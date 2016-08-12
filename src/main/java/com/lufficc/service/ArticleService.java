package com.lufficc.service;

import com.lufficc.model.Article;
import com.lufficc.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Page<Article> getPageableArticles(Pageable pageable) {
        PageRequest pageRequest = new PageRequest(
                pageable.getPageNumber(),
                pageable.getPageSize() > 10 ? 10 : pageable.getPageSize(),
                pageable.getSort()
        );
        return articleRepository.findAllPublishedArticles(pageRequest);
    }

    public void delete(Long id) {
        articleRepository.delete(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findOne(Long id) {
        return articleRepository.findOne(id);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public long count() {
        return articleRepository.count();
    }

    public long countPublished() {
        return articleRepository.countPublished();
    }

    public long countDraft() {
        return articleRepository.countDraft();
    }
}
