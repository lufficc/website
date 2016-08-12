package com.lufficc.model.support;

/**
 * Created by lcc_luffy on 2016/7/24.
 */
public enum ArticleStatus {
    PUBLISHED("Published"),
    DRAFT("Draft");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ArticleStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
