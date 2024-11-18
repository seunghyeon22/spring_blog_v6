package com.example.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardResponse {

    @Data
    public static class updateFormDTO{
        private int id;
        private String title;
        private String content;
        private String createdAt;

        public updateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = board.getCreatedAt().toString();// TODO: 2024.11.18 형태로 변경
        }
    }

    @Data
    public static class DetailDTO{
        private int id;
        private String title;
        private String content;
        private String createdAt;

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = board.getCreatedAt().toString();// TODO: 2024.11.18 형태로 변경
        }
    }


    @Data
    public static class DTO{
        private int id;
        private String title;


        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
