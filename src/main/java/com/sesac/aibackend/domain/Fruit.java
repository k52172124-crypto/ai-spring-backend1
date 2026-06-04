package com.sesac.aibackend.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fruit {

    private Long id;
    private String name;
    private int price;
    private String origin;
}
