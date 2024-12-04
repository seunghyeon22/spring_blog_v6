package com.example.blog.user;

import com.example.blog._core.error.ex.Exception401;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        if(userRepository.findByUsername(joinDTO.getUsername())==null){
            new Exception401("아이디가 있다.");
        }

        userRepository.save(joinDTO.toEntity());
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if(!user.getPassword().equals(loginDTO.getPassword())){
            new Exception401("비밀번호가 맞지 않습니다.");
        }
        return user;
    }
}
