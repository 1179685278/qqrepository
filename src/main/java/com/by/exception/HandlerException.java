package com.by.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gcq on 2019/7/3.
 */
@ControllerAdvice
public class HandlerException {

    @ExceptionHandler({UnauthorizedException.class})
    public ModelAndView getUnauthorizedException(Exception ex){
        ModelAndView mv = new ModelAndView();
        System.out.println("出现异常：" + ex);
        mv.setViewName("forward:/unauthorized.jsp");
        mv.addObject("ex",ex);
        return mv ;
    }
}
