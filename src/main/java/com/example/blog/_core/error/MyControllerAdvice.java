package com.example.blog._core.error;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception401;
import com.example.blog._core.error.ex.Exception404;
import com.example.blog._core.util.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 에러 처리
@RestControllerAdvice
public class MyControllerAdvice {

     @ExceptionHandler(Exception400.class)
    public ResponseEntity<Resp<?>> err400(Exception400 e) {
         ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        return rn;
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<Resp<?>> err404(Exception404 e) {
         ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.NOT_FOUND);
        return rn;
    }
    @ExceptionHandler(Exception401.class)
    public ResponseEntity<Resp<?>>  err401(Exception401 e) {
        ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.NOT_FOUND);
        return rn;
    }
}
