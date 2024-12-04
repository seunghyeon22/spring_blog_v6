package com.example.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@Import(BoartRepository.class)
@DataJpaTest // DB에 관련된 자원들을 메모리(Ioc)에 올린다.
public class BoardRepositoryTest {

    @Autowired
    private BoartRepository boartRepository;



//    @Test
//    public void findByIdJoinUser_Test(){
//        int id =1;
//       Board board = boartRepository.findByIdJoinUser(id);
//        System.out.println(board);
//    }
    @Test
    public void findByIdJoinUserAndReply_test(){
    //given
        Integer id = 1;

    //when
        Optional<Board> boardOP = boartRepository.findByIdJoinUserAndReply(id);
    //eye
    }

    @Test
    public void findById_test(){
        //given
        Integer id = 1;

        //when
        Optional<Board> boardOP = boartRepository.findById(id);
        Board board = boardOP.get();

        //eye
    }



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
    }

}