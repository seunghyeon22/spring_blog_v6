package com.example.blog.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;

import java.sql.Timestamp;

@AllArgsConstructor
@Table(name = "board_tb") // 언더스코어
@Entity
@Getter
@NoArgsConstructor  // DB에서 조회헤서 가져온 RS를 디폴트 생성자를 호출해서 new하고 리플렉션 해서 값을 채워준다.
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    @CreationTimestamp
    private Timestamp createdAt;

    public void update(String title, String content){
        this.title =title;
        this.content =content;
    }
}
