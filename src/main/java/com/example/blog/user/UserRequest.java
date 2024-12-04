package com.example.blog.user;

import lombok.Data;

public class UserRequest {

    @Data
    public class JoinDTO{
        private String username;
        private String password;
        private String email;

        public User toEntity(){
            return User.builder().username(username).password(password).email(email).build();
        }
    }
    @Data
    public class LoginDTO{
        private String username;
        private String password;

    }

}
