package com.example.blog.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class BoardRequest {

    @Data //getter,setter, toString
    public static class SaveDTO{
        @NotBlank(message = "title을 입력해주세요")
        private String title;
        @NotBlank(message = "content를 입력해주세요.")
        private String content;

        public Board toEntity(){
            return Board.builder().title(title).content(content).build();

        }
    }

    @Data //getter,setter, toString
    public static class updateDTO{
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }
}
