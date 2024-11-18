package com.example.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data //getter,setter, toString
    public static class SaveDTO{
        private String title;
        private String content;
    }

    @Data //getter,setter, toString
    public static class updateDTO{
        private String title;
        private String content;
    }
}
