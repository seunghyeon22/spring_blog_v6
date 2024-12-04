package com.example.blog.board._core.practice;

import org.junit.jupiter.api.Test;

class Member{
    private Integer id;
    private String name;
    private String addr;
    public static Member builder(){
        return new Member();
    }
    private Member(){

    }
    public Member id(Integer id){
        this.id = id;
        return this;
    }
    public Member name(String name){
        this.name = name;
        return this;
    }
    public Member addr(String addr){
        this.name = name;
        return this;
    }
}

public class BuilderTest {
    @Test
    public void new_test() {
        Member member = Member.builder().id(1).name("이름").addr("주소");
    }
}
