package com.example.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.beans.ConstructorProperties;
import java.util.Date;
import java.util.List;

@Data

public class Article {
    public Article() {
    }

    public Article(long id, String author, String title, String content, Date createTime) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    private long id;
    private String author;
    private String title;
    private String content;
    private Date createTime;

}
