package com.example.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository 책임 : DB 상호작용
@Repository
@RequiredArgsConstructor
public class BoartRepository {

   //JPA는 EntityManager로 DB에 접근한다.(JAVA에서 DBConnection)
    private final EntityManager em;

    public List<Board> findAll() {
        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        List<Board> list = q.getResultList();
        return list;
    }
    // 다운캐스팅의 조건 : 메모리에 올려져 있어야함
    public Board findById(int id) {
        Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        q.setParameter(1, id);// 물음표 완성하기(물음표 순서, 물음펴에 바인딩될 변수)
        return (Board)q.getSingleResult();
      //  return em.find(Board.class, id);
    }

    public void save(Board board) {
        em.persist(board); // 객체를 던지면 insert함
    }

    public void delete(int id){
        Query q = em.createNativeQuery("delete from board_tb where id = ?");
        q.setParameter(1, id);
        q.executeUpdate(); // insert, update, delete때 사용
    }

    public void update(int id, String title, String content) {
        Query q = em.createNativeQuery("update board_tb set title = ?, content = ? where id = ?");
        q.setParameter(1, title);
        q.setParameter(2, content);
        q.setParameter(3, id);
        q.executeUpdate();
    }
}
