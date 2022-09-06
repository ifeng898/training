package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class demoServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;
    @Resource
    private JdbcTemplate primarilyJdbcTemplate;
    @Resource
    private JdbcTemplate secondlylyJdbcTemplate;
    @Override
    public List<Article> findall() {
        List<Article> n = articleDao.findall(null);
        return n;
    }

    @Override
    public void saveArticle(Article article) {
        articleDao.saveArticle(article,null);
    }

    @Override
    public void delete() {
        articleDao.deleteArticle(null);
    }

    @Override
    public void delete(int id) {
        articleDao.deleteArticle(id,null);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article,null);
    }

    @Override
    public Article find(int id) {
        return articleDao.find(id,null);
    }
}
