package com.lufficc.controller;

import com.lufficc.model.User;
import com.lufficc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
@Controller
public class HomeController extends BaseController {
    /**
     * 未登录的话会有一个AnonymousAuthenticationToken认证
     */
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public String page() {
        return "page/index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doRegister(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("password_confirm") String password_confirm,
            RedirectAttributes attributes
    ) {
        attributes.addFlashAttribute("username", username);
        attributes.addFlashAttribute("email", email);
        if (StringUtils.isEmpty(username)) {
            danger(attributes, "用户名不能为空");
            return "redirect:/register";
        }
        if (StringUtils.isEmpty(email)) {
            danger(attributes, "邮箱不能为空");
            return "redirect:/register";
        }
        if (StringUtils.isEmpty(password) || !password.equals(password_confirm)) {
            danger(attributes, "密码不一致");
            return "redirect:/register";
        }
        if (userService.findByUsername(username) != null) {
            danger(attributes, "用户名已存在");
            return "redirect:/register";
        }
        if (userService.findEmail(email) != null) {
            danger(attributes, "邮箱已存在");
            return "redirect:/register";
        }
        User user = userService.createNewUser(username, email, password);
        if (user == null) {
            danger(attributes, "注册失败");
        } else {
            success(attributes, "注册成功");
        }
        return "redirect:/";
    }

}
