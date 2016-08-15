package com.lufficc.service;

import com.lufficc.model.Markdown;
import com.lufficc.repository.MarkdownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lufficc on 2016/8/15.
 */
@Service
public class MarkdownService {
    private final MarkdownRepository markdownRepository;

    @Autowired
    public MarkdownService(MarkdownRepository markdownRepository) {
        this.markdownRepository = markdownRepository;
    }

    public Markdown getMarkdown(Long id)
    {
        return markdownRepository.getOne(id);
    }

    public Markdown save(Markdown markdown)
    {
        return markdownRepository.save(markdown);
    }
}
