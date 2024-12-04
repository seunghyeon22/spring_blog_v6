package com.example.blog.user;

import com.example.blog._core.error.ex.Exception401;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findByUsername(String username) {
        Query query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        try {
            return (User) query.getSingleResult();
        } catch (RuntimeException e) {
            new Exception401("아이디 혹은 비밀번호가 맞지 않습니다.");
        }
        return (User) query.getSingleResult();
    }
}
