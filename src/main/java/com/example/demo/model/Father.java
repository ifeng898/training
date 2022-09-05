package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Father {
    @NotEmpty
    private String name;
    private int age;
}
