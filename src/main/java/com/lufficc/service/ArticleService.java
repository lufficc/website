package com.lufficc.service;

import com.lufficc.model.Article;
import com.lufficc.model.Category;
import com.lufficc.model.Folder;
import com.lufficc.model.Markdown;
import com.lufficc.model.form.ArticleForm;
import com.lufficc.model.support.ArticleStatus;
import com.lufficc.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@Service
public class ArticleService {
    private static final String CACHE_KEY = "article";

    private final ArticleRepository articleRepository;

    private final MarkdownService markdownService;

    private final CategoryService categoryService;

    private final FolderService folderService;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, MarkdownService markdownService, FolderService folderService, CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.markdownService = markdownService;
        this.folderService = folderService;
        this.categoryService = categoryService;
    }

    @Cacheable(CACHE_KEY)
    public Page<Article> getPageableArticles(int page, int size, Sort sort) {

        PageRequest pageRequest = new PageRequest(
                page,
                size > 10 ? 10 : size,
                sort
        );
        return articleRepository.findAllPublishedArticles(pageRequest);
    }
    @Cacheable(CACHE_KEY)
    public Page<Article> getPageableArticlesByCategory(Long category, int page, int size, Sort sort) {
        PageRequest pageRequest = new PageRequest(
                page,
                size > 10 ? 10 : size,
                sort
        );
        return articleRepository.findAllPublishedArticlesByCategory(categoryService.findOne(category), pageRequest);
    }

    @Cacheable(value = CACHE_KEY,key = "#folder_id.toString().concat(\"getArticleByFolder\")")
    public List<Article> getArticleByFolder(Long folder_id)
    {
        return articleRepository.findArticlesByFolder(folderService.findOne(folder_id));
    }

    @CacheEvict(value = CACHE_KEY,allEntries = true)
    public Article create(ArticleForm articleForm, String md) {
        Article article = generateArticle(null, articleForm);
        Markdown markdown = new Markdown(md);
        article.setMd(markdownService.save(markdown));
        save(article);
        return article;
    }

    @CacheEvict(value = CACHE_KEY,allEntries = true)
    public Article update(Long oldArticleId, ArticleForm articleForm, String md_content) {
        Article oldArticle = findOne(oldArticleId);
        generateArticle(oldArticle, articleForm);
        Markdown markdown = oldArticle.getMd();
        markdown.setMarkdown(md_content);
        oldArticle.setMd(markdownService.save(markdown));
        return save(oldArticle);
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

    @CacheEvict(value = CACHE_KEY,allEntries = true)
    public void delete(Long id) {
        articleRepository.delete(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Cacheable(value = CACHE_KEY,key = "#id.toString().concat(\"findOne\")")
    public Article findOne(Long id) {
        Article article = articleRepository.findOne(id);
        article.getMd();
        return article;
    }

    private Article save(Article article) {
        return articleRepository.save(article);
    }

    public Long count() {
        return articleRepository.count();
    }

    public long countPublished() {
        return articleRepository.countPublished();
    }

    public long countDraft() {
        return articleRepository.countDraft();
    }
}
