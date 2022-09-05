package com.example.demo.model;

import lombok.Data;

@Data
public class Child {
    private String name;
    private int age;
    private Friend[] friends;
}
