package com.example.blog.board;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller의 책임 : 외부 클라이언트의 요청을 받고 그 요청에 대한 응답을 함
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //was scope : application, session, request, page
    // session
    // request

    @GetMapping("/")
    public String list(Model model) { // DS(request 객체를 model이라는 객체로 랩핑해서 전달.)
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        model.addAttribute("models", boardList);

        return "list";
    }
    @GetMapping("/save-form")
    public String saveForm() {
        return "save-form";
    }

    @GetMapping("/board/{id}/update")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("model",boardService.게시글수정화면보기(id));
        return "update-form";
    }

    /**
     *  쿼리스트링(where절) : /board?title=바다
     *  패스변수(where절) :  /board/1
     */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("model",boardService.게시글상세보기(id));
        return "detail";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) { // x-www는 클래스로 받을 수 있다. json으로 받고 싶으면 @RequestBody를 사용
        System.out.println(saveDTO); // @Data는 내부에 toString를 재정의해서 구현
        boardService.게시글쓰기(saveDTO);

        return "redirect:/"; // 302코드를 줘야함 redirect
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        boardService.게시글삭제(id);
        return "redirect:/";
    }
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, BoardRequest.updateDTO updateDTO) {
        boardService.게시글수정(id,updateDTO);
        return "redirect:/";
    }

}
