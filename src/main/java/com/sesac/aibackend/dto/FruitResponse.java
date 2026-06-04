package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.Fruit;

public record FruitResponse(Long id, String name, int price, String origin) {

    public static FruitResponse from(Fruit fruit) {
        return new FruitResponse(fruit.getId(), fruit.getName(), fruit.getPrice(), fruit.getOrigin());
    }
}