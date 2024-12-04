package com.example.blog.board;

import com.example.blog.reply.Reply;
import com.example.blog.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "board_tb") // 언더스코어
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // DB에서 조회헤서 가져온 RS를 디폴트 생성자를 호출해서 new하고 리플렉션 해서 값을 채워준다.
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @OneToMany(mappedBy ="board", fetch = FetchType.EAGER) // fk 변수명이 뭐인지
    private List<Reply> replies = new ArrayList<Reply>();

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계
    private User user;
    @CreationTimestamp
    private Timestamp createdAt;

    public void update(String title, String content){
        this.title =title;
        this.content =content;
    }

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }


}
