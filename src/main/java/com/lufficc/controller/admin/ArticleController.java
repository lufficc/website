package com.lufficc.controller.admin;

import com.lufficc.controller.BaseController;
import com.lufficc.model.Article;
import com.lufficc.model.Category;
import com.lufficc.model.Folder;
import com.lufficc.model.form.ArticleForm;
import com.lufficc.model.support.ArticleStatus;
import com.lufficc.service.ArticleService;
import com.lufficc.service.CategoryService;
import com.lufficc.service.FolderService;
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

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FolderService folderService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "admin/article/index";
    }

    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findOne(id);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("content", article.getMarkdown());
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
        createArticle(articleForm, md);
        success(attributes, "创建成功");
        return "redirect:/";
    }


    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String doDelete(@RequestParam("id") long id) {
        articleService.delete(id);
        return "redirect:/admin/article";
    }


    @RequestMapping(value = "update/{id:[0-9]+}", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) {
        Article article = articleService.findOne(id);
        model.addAttribute("articleForm", ArticleForm.fromArticle(article));
        model.addAttribute("id", article.getId());
        model.addAttribute("md_content", article.getMarkdown());
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
        Article oldArticle = articleService.findOne(id);
        oldArticle = generateArticle(oldArticle, articleForm);
        oldArticle.setMarkdown(md_content);
        articleService.save(oldArticle);

        success(attributes, "修改成功");
        return "redirect:/";
    }

    private void createArticle(ArticleForm articleForm, String md) {
        Article article = generateArticle(null, articleForm);
        article.setMarkdown(md);
        articleService.save(article);
    }

    private Article generateArticle(Article article, ArticleForm articleForm) {
        Category category = categoryService.findByName(articleForm.getCategory());
        if (article == null)
            article = new Article(
                    articleForm.getTitle(),
                    articleForm.getDescription(),
                    articleForm.getAuthor(),
                    articleForm.getOriginUrl()
            );
        else {
            article.setTitle(articleForm.getTitle());
            article.setDescription(articleForm.getDescription());
            article.setAuthor(articleForm.getAuthor());
            article.setOriginUrl(articleForm.getOriginUrl());
        }
        article.setCategory(category);
        article.setArticleStatus(
                ArticleStatus.DRAFT.toString().equals(
                        articleForm.getArticleStatus()) ?
                        ArticleStatus.DRAFT : ArticleStatus.PUBLISHED
        );
        if (articleForm.getFolder() != -1) {
            Folder folder = folderService.findOne(articleForm.getFolder());
            article.setFolder(folder);
            article.setCategory(folder.getCategory());
        } else {
            article.setFolder(null);
        }
        return article;
    }
}
