package com.lufficc.controller.admin;

import com.lufficc.api.exception.NotFoundException;
import com.lufficc.controller.BaseController;
import com.lufficc.model.Article;
import com.lufficc.model.form.ArticleForm;
import com.lufficc.service.ArticleService;
import com.lufficc.service.CategoryService;
import com.lufficc.service.FolderService;
import com.lufficc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
@Controller
@RequestMapping("admin/article")
@Transactional
public class ArticleController extends BaseController {
    private static final String BASE_REDIRECT_URL = "redirect:/admin/article";

    private final ArticleService articleService;

    private final CategoryService categoryService;

    private final FolderService folderService;

    @Autowired
    public ArticleController(CategoryService categoryService, ArticleService articleService, FolderService folderService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.folderService = folderService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, name = "")
    public String index(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "admin/article/index";
    }

    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findOne(id);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("content", article.getMd().getMarkdown());
        return "page/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(ArticleForm articleForm, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("folders", folderService.findAll());
        return "admin/article/create";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String doCreate(@Valid ArticleForm articleForm, BindingResult result, @RequestParam("md_file") MultipartFile md_file, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("articleForm", articleForm);
            danger(attributes, "标*的不能为空");
            return BASE_REDIRECT_URL + "/create";
        }

        if (md_file.isEmpty()) {
            attributes.addFlashAttribute("articleForm", articleForm);
            danger(attributes, "文件不能为空");
            return BASE_REDIRECT_URL + "/create";
        }

        if (!md_file.getOriginalFilename().endsWith(".md")) {
            attributes.addFlashAttribute("articleForm", articleForm);
            danger(attributes, "文件名要以.md结尾");
            return BASE_REDIRECT_URL + "/create";
        }
        String md = null;
        try {
            md = new String(md_file.getBytes(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("articleForm", articleForm);
            danger(attributes, "文件解析出错");
            return BASE_REDIRECT_URL + "/create";
        }
        articleService.create(articleForm, md);
        success(attributes, "创建成功");
        return BASE_REDIRECT_URL;
    }


    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String doDelete(@RequestParam("id") long id, RedirectAttributes attributes) {
        articleService.delete(id);
        success(attributes, "删除成功");
        return "redirect:/admin/article";
    }


    @RequestMapping(value = "update/{id:[0-9]+}", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) throws NotFoundException {
        Article article = articleService.findOne(id);
        Utils.checkExists(article);
        model.addAttribute("articleForm", ArticleForm.fromArticle(article));
        model.addAttribute("id", article.getId());
        model.addAttribute("md_content", article.getMd().getMarkdown());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("folders", folderService.findAll());
        return "admin/article/update";
    }


    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.PUT)
    public String doUpdate(
            @Valid ArticleForm articleForm,
            BindingResult result,
            @PathVariable("id") long id,
            RedirectAttributes attributes,
            @RequestParam("md_content") String md_content) {
        if (result.hasErrors()) {
            danger(attributes, "标*的不能为空");
            return BASE_REDIRECT_URL + "/update/" + id;
        }
        if (md_content == null || md_content.trim().isEmpty()) {
            danger(attributes, "md内容不能为空");
            return BASE_REDIRECT_URL + "/update/" + id;
        }
        articleService.update(id, articleForm, md_content);
        success(attributes, "修改成功");
        return BASE_REDIRECT_URL;
    }

}
