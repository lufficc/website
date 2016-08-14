package com.lufficc.repository;

import com.lufficc.model.Article;
import com.lufficc.model.Category;
import com.lufficc.model.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("select a from Article a where a.articleStatus = 'Published'")
    Page<Article> findAllPublishedArticles(Pageable pageable);

    @Query("select a from Article a where a.articleStatus = 'Published' and a.category = ?1")
    Page<Article> findAllPublishedArticlesByCategory(Category category, Pageable pageable);

    @Query("select a from Article a where a.articleStatus = 'Published' and a.folder = ?1")
    List<Article> findArticlesByFolder(Folder folder);

    @Query("select count (a) from Article a where a.articleStatus = 'Published'")
    long countPublished();

    @Query("select count (a) from Article a where a.articleStatus = 'Draft'")
    long countDraft();

}
