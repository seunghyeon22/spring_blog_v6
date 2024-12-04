package com.example.blog.board;
import com.example.blog.reply.Reply;
import com.example.blog.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private Timestamp createdAt;

        private Integer userId;
        private String username;
        private boolean isOwner = false;

        private List<ReplyDTO> replies;

        @Data
        class ReplyDTO{
            private int id;
            private String comment;
            private int userId;
            private String username;

            public ReplyDTO(Reply reply) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
            }
        }


        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = board.getCreatedAt();

            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername(); // lazy loading
            if(sessionUser != null) {
                this.isOwner = sessionUser.getId() == board.getUser().getId();
            }
            this.replies = board.getReplies().stream().map(reply -> new ReplyDTO(reply)).toList();

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
