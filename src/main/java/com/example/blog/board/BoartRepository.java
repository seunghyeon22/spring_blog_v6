package com.example.blog.board;

import com.example.blog._core.error.ex.Exception404;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository 책임 : DB 상호작용
@Repository
@RequiredArgsConstructor
public class BoartRepository {

   //JPA는 EntityManager로 DB에 접근한다.(JAVA에서 DBConnection)
    private final EntityManager em;

    public  Optional<Board> findByIdJoinUserAndReply(int id){
        String sql = """
                select b from Board b join fetch b.user left join fetch b.replies r left join fetch r.user where b.id=:id
                """;
        Query query = em.createQuery(sql, Board.class);
        query.setParameter("id", id);
        try {
            Board board = (Board)query.getSingleResult();
            return Optional.ofNullable(board);
        }catch (RuntimeException e) {
            return Optional.ofNullable(null);
        }
    }

    public  Optional<Board> findByIdJoinUser(int id){
        String sql = """
                select b from Board b join fetch b.user where b.id=:id
                """;
        Query query = em.createQuery(sql, Board.class);
        query.setParameter("id", id);
        try {
           Board board = (Board)query.getSingleResult();
            return Optional.ofNullable(board);
        }catch (RuntimeException e) {
            return Optional.ofNullable(null);
        }
    }

    public List<Board> findAll() {
       return em.createQuery("select b from Board b order by b.id desc",Board.class).getResultList();
    }
    // 다운캐스팅의 조건 : 메모리에 올려져 있어야함
    public Optional<Board> findById(int id) {
        return Optional.ofNullable(em.find(Board.class, id));

    }

    public void save(Board board) {
        // 비영속
        em.persist(board); // 객체를 던지면 insert함
        // 동기화(영속화)
    }

    public void delete(int id){
        em.createQuery("delete from Board b where b.id=:id").setParameter("id", id).executeUpdate();
    }
}
