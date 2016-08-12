package com.lufficc.controller;

import com.lufficc.util.MsgType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
public class BaseController {
    public void d(Object msg) {
        System.out.println(msg);
    }

    public void success(RedirectAttributes attributes, String msg) {
        attributes.addFlashAttribute("msg", msg);
        attributes.addFlashAttribute("messageType", MsgType.SUCCESS);
    }

    public void info(RedirectAttributes attributes, String msg) {
        attributes.addFlashAttribute("msg", msg);
        attributes.addFlashAttribute("messageType", MsgType.INFO);
    }

    public void warning(RedirectAttributes attributes, String msg) {
        attributes.addFlashAttribute("msg", msg);
        attributes.addFlashAttribute("messageType", MsgType.WARNING);
    }

    public void danger(RedirectAttributes attributes, String msg) {
        attributes.addFlashAttribute("msg", msg);
        attributes.addFlashAttribute("messageType", MsgType.DANGER);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView normalException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("error", e.toString());
        return modelAndView;
    }
}
