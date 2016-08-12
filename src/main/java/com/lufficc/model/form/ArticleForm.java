package com.lufficc.model.form;

import com.lufficc.model.Article;
import com.lufficc.model.Folder;
import com.lufficc.model.support.ArticleStatus;

import javax.validation.constraints.Size;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
public class ArticleForm {
    @Size(min = 1)
    private String title;

    @Size(min = 1)
    private String description;

    @Size(min = 1)
    private String category;

    private String author;

    private String originUrl;

    private String articleStatus = ArticleStatus.PUBLISHED.toString();

    private Long folder;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Long getFolder() {
        return folder;
    }

    public void setFolder(Long folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", author='" + author + '\'' +
                ", originUrl='" + originUrl + '\'' +
                ", articleStatus='" + articleStatus + '\'' +
                ", folder=" + folder +
                '}';
    }

    public static ArticleForm fromArticle(Article article) {
        ArticleForm form = new ArticleForm();
        form.setTitle(article.getTitle());
        form.setDescription(article.getDescription());
        form.setCategory(article.getCategory().getName());
        Folder folder = article.getFolder();
        long folderId = folder == null ? -1 : folder.getId();
        form.setFolder(folderId);
        form.setAuthor(article.getAuthor());
        form.setOriginUrl(article.getOriginUrl());
        form.setArticleStatus(article.getArticleStatus().toString());
        return form;
    }
}
