package com.lufficc.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;

/**
 * Created by lufficc on 2016/8/15.
 */
@Entity
public class Markdown extends BaseModel{

    public Markdown(){}

    public Markdown(String markdown) {
        this.markdown = markdown;
    }

    @Type(type = "text")
    private String markdown;
    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    @Override
    public String toString() {
        return "Markdown{" +
                "markdown='" + markdown + '\'' +
                '}';
    }

    public static Markdown formMarkdown(Markdown markdown)
    {
        Markdown m = new Markdown(markdown.getMarkdown());
        m.setId(markdown.getId());
        m.setCreatedAt(markdown.getCreatedAt());
        m.setUpdatedAt(markdown.getUpdatedAt());
        return m;
    }
}
