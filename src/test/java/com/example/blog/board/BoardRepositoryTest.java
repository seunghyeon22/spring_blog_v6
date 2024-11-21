package com.example.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoartRepository.class)
@DataJpaTest // DB에 관련된 자원들을 메모리(Ioc)에 올린다.
public class BoardRepositoryTest {

    @Autowired
    private BoartRepository boartRepository;

    @Test
    public void findAll_test(){
        //given - findAll은 입력데이터 생략가능

        //when
        List<Board> list = boartRepository.findAll();
        System.out.println();
        for (Board board : list) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("============================");
        }

        //then

    }
    @Test
    public void saveTest(){
        Board boards = new Board(null,"ddd","nddd", null);
        boartRepository.save(boards);

        //Board board = boartRepository.findById(6);
        //System.out.println(board.getId());
        //System.out.println(board.getTitle());
        //System.out.println(board.getContent());
    }

    @Test
    public void deleteTest(){
        int id = 1;

        boartRepository.delete(id);
        List<Board> list = boartRepository.findAll();
        System.out.println("size : "+list.size());
    }

    @Test
    public void updateTest(){
        int id = 1;
        String title = "제목ㅇ";
        String content = "sodud";
//        boartRepository.update(id,title,content);
//        Board board = boartRepository.findById(id);
//        System.out.println(board.getTitle());
    }

} // rollback(@Transactional)
