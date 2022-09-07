package com.example.demo.controller;

import com.example.demo.AjaxResponse;
import com.example.demo.dao.testdb.Article;
import com.example.demo.model.ArticleVO;
import com.example.demo.service.demoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

// 本节重点解释 restful 的接口协议标准 post get put delete 共四种方式 post:insert get:query put:update
@Slf4j
@RestController
@RequestMapping("/restful")
public class ArticleController {
    //    根据id 查询文章
    @Resource
    private demoServiceImpl demoService;

    @GetMapping("/articles/{id}")
    public AjaxResponse getArticle(@PathVariable("id") int id) {
//        Article marticle = Article.builder()
//                .title("tile01")
//                .author("xiaogang")
//                .id(id)
//                .content("content 01")
//                .createTime(new Date()).build();
//        log.info("article" + marticle);
        ArticleVO m = demoService.find(id);
        log.info("article" + m);
        return AjaxResponse.sucess(m, "query sucess");
    }
    @GetMapping("/articles")
    public AjaxResponse getArticle() {
//        Article marticle = Article.builder()
//                .title("tile01")
//                .author("xiaogang")
//                .id(id)
//                .content("content 01")
//                .createTime(new Date()).build();
//        log.info("article" + marticle);
        List<Article> m = demoService.findall();
        log.info("article" + m);
        return AjaxResponse.sucess(m, "query all sucess");
    }

    //这种市接收body-raw-joson 数据格式的方式 优势：可以接受对象内嵌套对象列表，因此简要这种方式
//    @PostMapping("/articles")
//    public AjaxResponse saveArticle(@RequestBody Article article) {
//        log.info("save article "+article);
//        return AjaxResponse.sucess(article,"save sucess");
//    }
    //这种是传参接受body-form-data 或 params  这2种方式 收数据
    @PostMapping("/articles")
    public AjaxResponse saveArticle(
            @RequestParam int id,
            @RequestParam String author,
                                    @RequestParam String title,
                                    @RequestParam String content,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                    @RequestParam Date createtime
    ) {
        log.info("save article " + createtime);
        demoService.saveArticle(Article.builder().id(id).title(title).author(author).content(content).createTime(createtime).build());
        return AjaxResponse.sucess(null, "save sucess");
    }
     //这种市接收body-raw-joson 数据格式的方式 优势：可以接受对象内嵌套对象列表，因此简要这种方式
//    @PutMapping("/articles")
//    public AjaxResponse updateArticle(@RequestBody Article article) {
//        log.info("update article " + article);
//        demoService.updateArticle(article);
//        return AjaxResponse.sucess(article, "update sucess");
//    }

    @PutMapping("/articles")
    public AjaxResponse updateArticle(@RequestParam int id,
                                      @RequestParam String author,
                                      @RequestParam String title,
                                      @RequestParam String content,
                                      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                          @RequestParam Date createtime) {
       /* if (article.getId() == null){
            //todo
        }*/

        Article article=Article.builder().id(id).title(title).author(author).content(content).createTime(createtime).build();
        log.info("update article " + article);
        demoService.updateArticle(article);
        return AjaxResponse.sucess(article, "update sucess");
    }

    @DeleteMapping("/articles/{id}")
    public AjaxResponse deleteArticle(@PathVariable int id) {
        log.info("delete article " + id);
        demoService.delete(id);
        return AjaxResponse.sucess(null, "delete sucess");
    }

    @DeleteMapping("/articles")
    public AjaxResponse deleteArticle() {
        log.info("delete all article " );
        demoService.delete();
        return AjaxResponse.sucess(null, "delete sucess");
    }
}
