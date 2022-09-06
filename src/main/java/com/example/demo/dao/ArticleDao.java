package com.example.demo.dao;

import com.example.demo.model.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ArticleDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Article> findall() {
        return jdbcTemplate.query("SELECT * FROM Article ", new BeanPropertyRowMapper<>(Article.class));
    }
    public  Article find(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM Article WHERE id = ?",new Object[]{id},new BeanPropertyRowMapper<>(Article.class));
    }
    public void saveArticle(Article article) {
        jdbcTemplate.update("INSERT INTO Article(id,author,title,content,createTime) values (?,?,?,?,?)",
                article.getId()
                ,article.getAuthor()
                , article.getTitle()
                , article.getContent()
                , article.getCreateTime());
    }

    public void deleteArticle() {
        jdbcTemplate.update("DELETE  FROM Article");
    }

    public void deleteArticle(int id) {
        jdbcTemplate.update("DELETE FROM Article WHERE id = ?", id);
    }

    public void updateArticle(Article article) {
        jdbcTemplate.update("update Article SET author = ?,title = ?, content = ?, createTime = ? where id = ?",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime(),
                article.getId());
    }
}
