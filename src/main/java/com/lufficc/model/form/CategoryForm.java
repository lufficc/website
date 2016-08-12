package com.lufficc.model.form;

import javax.validation.constraints.Size;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
public class CategoryForm {
    @Size(min = 1)
    private String name;

    @Size(min = 1)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CategoryForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
