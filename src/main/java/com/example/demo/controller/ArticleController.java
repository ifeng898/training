package com.example.demo.controller;

import com.example.demo.AjaxResponse;
import com.example.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
// 本节重点解释 restful 的接口协议标准 post get put delete 共四种方式 post:insert get:query put:update
@Slf4j
@RestController
@RequestMapping("/restful")
public class ArticleController {
    //    根据id 查询文章
    @GetMapping("/articles/{id}")
    public AjaxResponse getArticle(@PathVariable("id") long id) {
        Article marticle = Article.builder()
                .title("tile01")
                .author("xiaogang")
                .id(id)
                .content("content 01")
                .createTime(new Date()).build();
        log.info("article" + marticle);

        return AjaxResponse.sucess(marticle, "query sucess");
    }
    //这种市接收body-raw-joson 数据格式的方式 优势：可以接受对象内嵌套对象列表，因此简要这种方式
//    @PostMapping("/articles")
//    public AjaxResponse saveArticle(@RequestBody Article article) {
//        log.info("save article "+article);
//        return AjaxResponse.sucess(article,"save sucess");
//    }
    //这种是传参接受body-form-data 或 params  这2种方式 收数据
    @PostMapping("/articles")
    public AjaxResponse saveArticle(@RequestParam String author,
                                        @RequestParam String title,
                                        @RequestParam String content,
                                        @DateTimeFormat (pattern="yyyy-MM-dd HH:mm:ss")
                                        @RequestParam Date createtime
                                         ) {
        log.info("save article "+createtime);
        return AjaxResponse.sucess(null,"save sucess");
    }
    @PutMapping("/articles")
    public AjaxResponse updateArticle(@RequestBody Article article) {
       /* if (article.getId() == null){
            //todo
        }*/
        log.info("update article "+article);
        return AjaxResponse.sucess(article,"update sucess");
    }
    @DeleteMapping("/articles/{id}")
    public AjaxResponse deleteArticle(@PathVariable long id) {
        log.info("delete article "+id);
        return AjaxResponse.sucess(null,"delete sucess");
    }
}
