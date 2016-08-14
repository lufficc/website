package com.lufficc.api;

import com.lufficc.api.exception.NotFoundException;
import com.lufficc.api.model.JsonWrap;
import com.lufficc.api.model.PagedJson;
import com.lufficc.model.Article;
import com.lufficc.service.ArticleService;
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
@RequestMapping("api/article")
public class ArticleApiController extends BaseApiController {
    private final ArticleService articleService;

    @Autowired
    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public PagedJson<List<Article>> getArticles(Pageable pageable) {
        Page<Article> articles = articleService.getPageableArticles(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort()
        );
        PagedJson<List<Article>> pagedJson = new PagedJson<>(HttpStatus.OK.value(), "success", articles.getContent());
        pagedJson.fillData(articles);
        return pagedJson;
    }


    @RequestMapping(value = "/{id:[0-9]+]}", method = RequestMethod.GET)
    public JsonWrap<Article> getArticle(@PathVariable("id") Long id) throws NotFoundException {
        Article article = articleService.findOne(id);
        if (article == null)
            throw new NotFoundException("article with id " + id + " not fount");
        return new JsonWrap<>(HttpStatus.OK.value(), "success", article);
    }

}
