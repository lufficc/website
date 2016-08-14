package com.lufficc.api;

import com.lufficc.api.exception.NotFoundException;
import com.lufficc.api.model.JsonWrap;
import com.lufficc.api.model.PagedJson;
import com.lufficc.model.Article;
import com.lufficc.model.Category;
import com.lufficc.service.ArticleService;
import com.lufficc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@RestController
@Transactional
@RequestMapping("api/category")
public class CategoryApiController extends BaseApiController {

    private final CategoryService categoryService;
    private final ArticleService articleService;

    @Autowired
    public CategoryApiController(CategoryService categoryService, ArticleService articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonWrap<List<Category>> getCategories() {
        List<Category> categories = categoryService.findAll();
        return new JsonWrap<>(HttpStatus.OK.value(), "success", categories);
    }


    @RequestMapping(value = "/{category_id:[0-9]+}/article", method = RequestMethod.GET)
    public PagedJson<List<Article>> getArticlesByCategory(@PathVariable("category_id") Long category_id, Pageable pageable) {
        Page<Article> articles = articleService.getPageableArticlesByCategory(
                category_id,
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort()
        );
        PagedJson<List<Article>> pagedJson = new PagedJson<>(HttpStatus.OK.value(), "success", articles.getContent());
        pagedJson.fillData(articles);
        return pagedJson;
    }

    @RequestMapping(value = "/{id:[0-9]+]}", method = RequestMethod.GET)
    public JsonWrap<Category> getCategory(@PathVariable("id") Long id) throws NotFoundException {
        Category category = categoryService.findOne(id);
        if (category == null)
            throw new NotFoundException("article with id " + id + " not fount");
        return new JsonWrap<>(HttpStatus.OK.value(), "success", category);
    }
}
