package com.example.blog.board;



import com.example.blog._core.error.ex.Exception400;
import com.example.blog.user.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller의 책임 : 외부 클라이언트의 요청을 받고 그 요청에 대한 응답을 함
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;
    //was scope : application, session, request, page
    // session
    // request

    @GetMapping("/")
    public String list(Model model) { // DS(request 객체를 model이라는 객체로 랩핑해서 전달.)
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        model.addAttribute("models", boardList);

        return "index";
    }
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}/update")
    public String updateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("model",boardService.게시글수정화면보기(id));
        return "board/update-form";
    }

    /**
     *  쿼리스트링(where절) : /board?title=바다
     *  패스변수(where절) :  /board/1
     */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id, sessionUser);
        model.addAttribute("model", boardDetail);
        return "board/detail";
    }

    @PostMapping("/board/save")
    public String saveV2(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {

        boardService.게시글쓰기(saveDTO);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        boardService.게시글삭제(id);
        return "redirect:/";
    }
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, @Valid BoardRequest.updateDTO updateDTO, Errors errors) {
        boardService.게시글수정(id,updateDTO);
        return "redirect:/";
    }

}
