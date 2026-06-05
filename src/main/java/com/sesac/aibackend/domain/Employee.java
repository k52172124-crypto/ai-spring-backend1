package com.sesac.aibackend.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Employee {
    private Long id;
    private String name;
    private  Department department;

    public Employee(){
    }

    public Employee(Long id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }
}
