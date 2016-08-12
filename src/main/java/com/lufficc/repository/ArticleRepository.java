package com.lufficc.repository;

import com.lufficc.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("select a from Article a where a.articleStatus = 'Published' order by a.createdAt desc")
    Page<Article> findAllPublishedArticles(Pageable pageable);

    @Query("select count (a) from Article a where a.articleStatus = 'Published'")
    public long countPublished();

    @Query("select count (a) from Article a where a.articleStatus = 'Draft'")
    public long countDraft();
}
