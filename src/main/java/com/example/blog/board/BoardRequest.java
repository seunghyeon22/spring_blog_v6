package com.example.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data //getter,setter, toString
    public static class SaveDTO{
        private String title;
        private String content;

        public Board toEntity(){
                Board board = new Board(null,title, content, null);
                return board;
        }
    }

    @Data //getter,setter, toString
    public static class updateDTO{
        private String title;
        private String content;
    }
}
