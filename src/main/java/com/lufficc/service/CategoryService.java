package com.lufficc.service;

import com.lufficc.model.Category;
import com.lufficc.model.form.CategoryForm;
import com.lufficc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@Service
public class CategoryService {
    private static final String CACHE_KEY = "category";

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> getPageableCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Cacheable(CACHE_KEY)
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    @Cacheable(CACHE_KEY)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Cacheable(CACHE_KEY)
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @CacheEvict(value = CACHE_KEY, allEntries = true)
    public Category create(CategoryForm categoryForm) {
        Category category = new Category(categoryForm.getName().trim(), categoryForm.getDescription().trim());
        save(category);
        return category;
    }

    @CacheEvict(value = CACHE_KEY, allEntries = true)
    public Category update(Category category, CategoryForm categoryForm) {
        category.setName(categoryForm.getName().trim());
        category.setDescription(categoryForm.getDescription().trim());
        return save(category);
    }

    private Category save(Category category) {
        return categoryRepository.save(category);
    }

    public long count() {
        return categoryRepository.count();
    }

    @CacheEvict(value = CACHE_KEY, allEntries = true)
    public void delete(Long id) {
        categoryRepository.delete(id);
    }
}
