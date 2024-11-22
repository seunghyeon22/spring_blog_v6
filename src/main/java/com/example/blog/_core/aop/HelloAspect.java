package com.example.blog._core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {


    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void numHello(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Integer) {
                Integer i = (Integer) arg;
                if(i!=null){
                    System.out.println(i+"야 안녕");
                }
            }
        }


    }


}
