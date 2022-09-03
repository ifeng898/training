package com.example.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class Article {

    private long id;
    private String author;
    private String title;
    private String content;
    private Date createTime;

}
