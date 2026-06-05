package com.sesac.aibackend.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {
    private Long id;
    private  String name;

    public Department() {
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
