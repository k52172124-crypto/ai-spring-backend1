package com.sesac.aibackend.controller;

import com.sesac.aibackend.domain.Fruit;
import com.sesac.aibackend.dto.FruitRequest;
import com.sesac.aibackend.dto.FruitResponse;
import com.sesac.aibackend.error.NotFoundException;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/legacy/Fruit")
public class FruitController {

    private final Map<Long, Fruit> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @GetMapping
    public List<FruitResponse> list() {
        return storage.values().stream().map(FruitResponse::from).toList();
    }

    @GetMapping("/{id}")
    public FruitResponse get(@PathVariable Long id) {
        Fruit fruit = storage.get(id);
        if (fruit == null) {
            throw NotFoundException.of("item", id);
        }
        return FruitResponse.from(fruit);
    }

    @PostMapping
    public ResponseEntity<FruitResponse> create(@Valid @RequestBody FruitRequest req) {
        long id = sequence.getAndIncrement();
        Fruit saved = Fruit.builder().id(id).name(req.name()).price(req.price()).build();
        storage.put(id, saved);
        return ResponseEntity.created(URI.create("/legacy/fruit/" + id)).body(FruitResponse.from(saved));
    }

    @PutMapping("/{id}")
    public FruitResponse update(@PathVariable Long id, @Valid @RequestBody FruitRequest req) {
        Fruit existing = storage.get(id);
        existing.setName(req.name());
        existing.setPrice(req.price());
        return FruitResponse.from(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (storage.remove(id) == null) {
            throw NotFoundException.of("fruit", id);
        }
        return ResponseEntity.noContent().build();
    }
}
