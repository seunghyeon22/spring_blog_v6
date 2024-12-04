package com.example.blog.board;

import com.example.blog._core.error.ex.Exception404;
import com.example.blog.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Service의 책임 : 비지니스 로직 처리(트렌젝션 관리), DTO
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoartRepository boardRepository;

    public List<BoardResponse.DTO> 게시글목록보기(){
        return boardRepository.findAll().stream()
                .map(BoardResponse.DTO::new)
                .toList();
    };

    public BoardResponse.DetailDTO 게시글상세보기(int id, User sessionUser) {
        Board board = boardRepository.findByIdJoinUserAndReply(id)
                .orElseThrow(() -> new Exception404("해당 id의 게시글이 없습니다 : "+id));
        return new BoardResponse.DetailDTO(board, sessionUser);
    }

    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.toEntity());
    }

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.delete(id);
    }

    @Transactional
    public void 게시글수정(int id, BoardRequest.updateDTO updateDTO) {
        Board board =boardRepository.findById(id).orElseThrow(() ->new Exception404("해당 ID의 게시글이 없습니다." + id));
        board.update(updateDTO.getTitle(), updateDTO.getContent());
    } // 영속화된 객체 상태 변화 - update+commit => 더티체킹

    public BoardResponse.updateFormDTO 게시글수정화면보기(int id) {
        return new BoardResponse.updateFormDTO(boardRepository.findById(id).orElseThrow(() ->new Exception404("해당 ID의 게시글이 없습니다. : " + id)));
    }
}
