package com.lufficc.controller.admin;

import com.lufficc.service.ArticleService;
import com.lufficc.service.CategoryService;
import com.lufficc.service.FolderService;
import com.lufficc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("articleCount", articleService.count());
        model.addAttribute("categoryCount", categoryService.count());
        model.addAttribute("folderCount", folderService.count());
        model.addAttribute("userCount", userService.count());
        return "admin/index";
    }
}
