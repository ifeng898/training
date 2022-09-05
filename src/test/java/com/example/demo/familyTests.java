package com.example.demo;

import com.example.demo.model.family;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class familyTests {
@Resource
family mfamily;
    @Test
    public void familytest() throws Exception {
        System.out.println(mfamily.toString());
    }

}
