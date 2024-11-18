package com.example.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// Service의 책임 : 비지니스 로직 처리(트렌젝션 관리), DTO
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoartRepository boardRepository;

    public List<BoardResponse.DTO> 게시글목록보기(){
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponse.DTO> boardDtoList = new ArrayList<>();

        for(Board board : boardList){
            BoardResponse.DTO boardDto = new BoardResponse.DTO(board);
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    };
    public BoardResponse.updateFormDTO 게시글수정화면보기(int id) {
        Board board = boardRepository.findById(id);
        return new BoardResponse.updateFormDTO(board);
    }

    public BoardResponse.DetailDTO 게시글상세보기(int id) {
        Board board = boardRepository.findById(id);
        return new BoardResponse.DetailDTO(board);
    }

    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.getTitle(),saveDTO.getContent());
    } // commit

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.delete(id);
    }

    public void 게시글수정(int id, BoardRequest.updateDTO updateDTO) {
        boardRepository.update(id, updateDTO.getTitle(),updateDTO.getContent());
    }
}
