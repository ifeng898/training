package com.example.demo.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "family")
public class family {
    private String familyname;
    private  Father father;
    private Mother mother;
    private Child child;
}
