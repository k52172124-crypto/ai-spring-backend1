package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.Fruit;

public record FruitRequest (
        String name,
        int price,
        String origin
) {
    public Fruit toEntity() {
        return Fruit.builder().name(name).price(price).origin(origin).build();
    }
}