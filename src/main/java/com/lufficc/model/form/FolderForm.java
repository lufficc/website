package com.lufficc.model.form;

import com.lufficc.model.Folder;

import javax.validation.constraints.Size;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
public class FolderForm {
    @Size(min = 1)
    private String name;

    @Size(min = 1)
    private String description;

    @Size(min = 1)
    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static FolderForm fromFolder(Folder folder) {
        FolderForm form = new FolderForm();
        form.setName(folder.getName());
        form.setDescription(folder.getDescription());
        if (folder.getCategory() != null)
            form.setCategory(folder.getCategory().getName());
        return form;
    }

    @Override
    public String toString() {
        return "FolderForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
