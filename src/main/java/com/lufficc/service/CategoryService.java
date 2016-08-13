package com.lufficc.service;

import com.lufficc.model.Category;
import com.lufficc.model.form.CategoryForm;
import com.lufficc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getPageableCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category create(CategoryForm categoryForm) {
        Category category = new Category(categoryForm.getName().trim(), categoryForm.getDescription().trim());
        save(category);
        return category;
    }

    public Category update(Category category,CategoryForm categoryForm)
    {
        category.setName(categoryForm.getName().trim());
        category.setDescription(categoryForm.getDescription().trim());
        return save(category);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public long count() {
        return categoryRepository.count();
    }

    public void delete(Long id) {
        categoryRepository.delete(id);
    }
}
