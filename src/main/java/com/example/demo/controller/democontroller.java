package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class democontroller {
    @RequestMapping("/hello")
    public String Hello(String name){
        return " fhello worl 4x0000g"+"0000";
    }
}
