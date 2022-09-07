package com.example.demo.dao.testdb2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name="ABC")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,length = 32)
    private String author;
    @Column(nullable = false,length = 64)
    private String title;
    @Column(length = 256)
    private String content;
    private Date createTime;

}
