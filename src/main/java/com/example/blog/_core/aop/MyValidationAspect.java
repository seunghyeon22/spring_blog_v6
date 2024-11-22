package com.example.blog._core.aop;

import com.example.blog._core.error.ex.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Aspect
@Component
public class MyValidationAspect {


    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    // Around는 JoinPoint가 아니라 ProceedingJoinPoint 사용
    public void validationCheck(JoinPoint jp) throws Throwable {
         Object[] args =jp.getArgs();
         for(Object arg : args) {
             if(arg instanceof Errors) {
                 Errors errors = (Errors) arg;
                 if (errors.hasErrors()) {
                     String errMsg = errors.getFieldErrors().get(0).getField() +" : "+errors.getFieldErrors().get(0).getDefaultMessage();
                     throw new Exception400(errMsg);
                 }
             }
         }
    }
}
