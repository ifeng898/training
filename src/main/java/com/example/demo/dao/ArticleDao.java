package com.example.demo.dao;

import com.example.demo.dao.testdb.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ArticleDao {
    @Resource
    private JdbcTemplate primarilyJdbcTemplate;

    public List<Article> findall(JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null){
            jdbcTemplate=primarilyJdbcTemplate;
        }
        return jdbcTemplate.query("SELECT * FROM Article ", new BeanPropertyRowMapper<>(Article.class));
    }
    public  Article find(int id,JdbcTemplate jdbcTemplate){

        if (jdbcTemplate == null){
            jdbcTemplate=primarilyJdbcTemplate;
        }
        return jdbcTemplate.queryForObject("SELECT * FROM Article WHERE id = ?",new Object[]{id},new BeanPropertyRowMapper<>(Article.class));
    }
    public void saveArticle(Article article,JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null){
            jdbcTemplate=primarilyJdbcTemplate;
        }
        jdbcTemplate.update("INSERT INTO Article(id,author,title,content,createTime) values (?,?,?,?,?)",
                article.getId()
                ,article.getAuthor()
                , article.getTitle()
                , article.getContent()
                , article.getCreateTime());
    }

    public void deleteArticle(JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null){
            jdbcTemplate=primarilyJdbcTemplate;
        }
        jdbcTemplate.update("DELETE  FROM Article");
    }

    public void deleteArticle(int id,JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null){
            jdbcTemplate=primarilyJdbcTemplate;
        }
        jdbcTemplate.update("DELETE FROM Article WHERE id = ?", id);
    }

    public void updateArticle(Article article,JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null){
            jdbcTemplate=primarilyJdbcTemplate;
        }
        jdbcTemplate.update("update Article SET author = ?,title = ?, content = ?, createTime = ? where id = ?",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime(),
                article.getId());
    }
}
