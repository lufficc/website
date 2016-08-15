package com.lufficc.service;

import com.lufficc.model.Category;
import com.lufficc.model.Folder;
import com.lufficc.model.form.FolderForm;
import com.lufficc.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@Service
public class FolderService {
    private static final String CACHE_KEY = "folder";

    private final FolderRepository folderRepository;

    private final CategoryService categoryService;

    @Autowired
    public FolderService(FolderRepository folderRepository, CategoryService categoryService) {
        this.folderRepository = folderRepository;
        this.categoryService = categoryService;
    }
    @CacheEvict(value = CACHE_KEY, allEntries = true)
    public Folder create(FolderForm folderForm) {
        Folder folder = new Folder(folderForm.getName().trim(), folderForm.getDescription().trim());
        folder.setCategory(categoryService.findByName(folderForm.getCategory()));
        return save(folder);
    }

    @CacheEvict(value = CACHE_KEY, allEntries = true)
    public Folder update(Folder oldFolder, FolderForm folderForm) {
        Category category = categoryService.findByName(folderForm.getCategory());
        oldFolder.setName(folderForm.getName());
        oldFolder.setDescription(folderForm.getDescription());
        oldFolder.setCategory(category);
        return save(oldFolder);
    }

    @Cacheable(CACHE_KEY)
    public Page<Folder> getPageableFolders(int page, int size, Sort sort) {
        return folderRepository.findAll(new PageRequest(page, size > 10 ? 10 : size, sort));
    }

    @Cacheable(CACHE_KEY)
    public Folder findOne(Long id) {
        return folderRepository.findOne(id);
    }

    @Cacheable(CACHE_KEY)
    public List<Folder> findAll() {
        return folderRepository.findAll();
    }

    @Cacheable(CACHE_KEY)
    public Folder findByName(String name) {
        return folderRepository.findByName(name);
    }

    private Folder save(Folder folder) {
        return folderRepository.save(folder);
    }

    public long count() {
        return folderRepository.count();
    }

    @CacheEvict(value = CACHE_KEY, allEntries = true)
    public void delete(Long id) {
        folderRepository.delete(id);
    }
}
