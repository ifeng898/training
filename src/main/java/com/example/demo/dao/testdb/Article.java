package com.example.demo.dao.testdb;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name="ABC")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,length = 32)
    private String author;
    @Column(nullable = false,length = 64)
    private String title;
    @Column(length = 256)
    private String content;
    private Date createTime;

}
