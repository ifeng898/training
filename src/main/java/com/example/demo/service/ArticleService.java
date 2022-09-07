package com.example.demo.service;

import com.example.demo.dao.testdb.Article;
import com.example.demo.model.ArticleVO;

import java.util.List;

public interface ArticleService {
    public List<Article> findall();
    public void saveArticle(Article article);
    public void delete();
    public void delete(int id);
    public  void updateArticle(Article article);
    public ArticleVO find(int id);

}
