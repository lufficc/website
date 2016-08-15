package com.lufficc.repository;

import com.lufficc.model.Markdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by lufficc on 2016/8/15.
 */
@Repository
public interface MarkdownRepository extends JpaRepository<Markdown,Long>
{

}
