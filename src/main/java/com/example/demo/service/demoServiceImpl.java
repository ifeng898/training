package com.example.demo.service;

import com.example.demo.dao.testdb.ArtcleRepository;
import com.example.demo.dao.testdb.Article;
import com.example.demo.dao.testdb2.Message;
import com.example.demo.dao.testdb2.MessageRepository;
import com.example.demo.model.ArticleVO;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class demoServiceImpl implements ArticleService {
    @Resource
    private Mapper dozeMapper;
    @Resource
    private ArtcleRepository artcleRepository;
    @Resource
    private MessageRepository messageRepository;

    @Override
    public List<Article> findall() {
        List<Article> n = artcleRepository.findAll();//.findall(null);
        return n;
    }

    @Transactional
    @Override
    public void saveArticle(Article article) {
        artcleRepository.save(article);
        messageRepository.save(new Message().builder().title("mess").content("mess 123445").createTime(new Date()).author("abc").build());
        //这里故意设置异常 以此验证数据库事务是否同步正常，预期是以上两条数据都不会插入到数据库
        int f = 1 / 0;
        //  articleDao.saveArticle(article,null);
    }

    @Override
    public void delete() {
        artcleRepository.deleteAll();
    }

    @Override
    public void delete(int id) {
        artcleRepository.deleteById((long) id);
    }

    @Override
    public void updateArticle(Article article) {
        artcleRepository.save(article);
    }

    @Override
    public ArticleVO find(int id) {

        Optional<Article> m = artcleRepository.findById(Long.valueOf(id));

        return dozeMapper.map(m.get(), ArticleVO.class);
    }
}
