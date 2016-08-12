package com.lufficc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lufficc.model.support.ArticleStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lcc_luffy on 2016/7/23.
 */
@Entity
/*@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})*/
public class Article extends BaseModel implements Serializable {

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private String author;

    private String originUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private ArticleStatus articleStatus = ArticleStatus.PUBLISHED;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Folder folder;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    @Type(type = "text")
    private String markdown;

    public Article() {
    }

    public Article(String title, String description, String author, String originUrl) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.originUrl = originUrl;
    }
}
