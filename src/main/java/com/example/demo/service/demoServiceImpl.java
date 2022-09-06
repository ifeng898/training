package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class demoServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    @Override
    public List<Article> findall() {
        List<Article> n = articleDao.findall();
        return n;
    }

    @Override
    public void saveArticle(Article article) {
        articleDao.saveArticle(article);
    }

    @Override
    public void delete() {
        articleDao.deleteArticle();
    }

    @Override
    public void delete(int id) {
        articleDao.deleteArticle(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    public Article find(int id) {
        return articleDao.find(id);
    }
}
