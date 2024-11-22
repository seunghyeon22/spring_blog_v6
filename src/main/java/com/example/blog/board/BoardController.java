package com.example.blog.board;


import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.util.Resp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller의 책임 : 외부 클라이언트의 요청을 받고 그 요청에 대한 응답을 함
@CrossOrigin()
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //was scope : application, session, request, page
    // session
    // request

    @GetMapping("/api")
    public Resp<?> list() {
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        return Resp.ok(boardList);
    }
    @PostMapping("/api/board")
    public Resp<?> save(@Valid @RequestBody BoardRequest.SaveDTO saveDTO, Errors errors) {
        boardService.게시글쓰기(saveDTO);
        return Resp.ok(null);
    }

    @GetMapping("/api/board/{id}")
    public Resp<?> detail(@PathVariable("id") int id ) {
       BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        return Resp.ok(boardDetail);
    }

    @PutMapping("/api/board/{id}")
    public Resp<?> update(@PathVariable("id") int id, @Valid @RequestBody BoardRequest.updateDTO updateDTO, Errors errors) {
        boardService.게시글수정(id,updateDTO);
        return Resp.ok(null);
    }

    @DeleteMapping("/api/board/{id}")
    public Resp<?> delete(@PathVariable("id") int id) {
        boardService.게시글삭제(id);
        return Resp.ok(null);
    }
}
