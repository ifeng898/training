package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class Child {
    @NotEmpty
    private String name;
    @Min(1)
    private int age;
    private Friend[] friends;
}
