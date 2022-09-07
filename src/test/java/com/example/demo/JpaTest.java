package com.example.demo;

import com.example.demo.dao.testdb.ArtcleRepository;
import com.example.demo.dao.testdb.Article;
import com.example.demo.dao.testdb2.Message;
import com.example.demo.dao.testdb2.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class JpaTest {
    @Resource
   private ArtcleRepository artcleRepository;
    @Resource
    private MessageRepository messageRepository;
    @Test
    public void jpaTest(){


        Object tm = messageRepository.save(
                Message.builder()
                        .title("abc")
                        .createTime(new Date())
                        .author("zuohe message")
                        .content("fsdsfnsofos")
                        .build());

        System.out.println(tm.toString()+" ---");

        artcleRepository.save(
                Article.builder()
                        .title("abc")
                        .createTime(new Date())
                        .author("zuohe")
                        .content("fsdsfnsofos")
                        .build());
    }
}

