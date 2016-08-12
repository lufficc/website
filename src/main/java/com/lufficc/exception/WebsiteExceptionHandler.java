package com.lufficc.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by lcc_luffy on 2016/8/9.
 */
public class WebsiteExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        model.addAttribute("msg", e.getMessage());
        return "404";
    }
}
