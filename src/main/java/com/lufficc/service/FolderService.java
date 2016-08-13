package com.lufficc.service;

import com.lufficc.model.Category;
import com.lufficc.model.Folder;
import com.lufficc.model.form.FolderForm;
import com.lufficc.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@Service
public class FolderService {
    private final FolderRepository folderRepository;

    private final CategoryService categoryService;

    @Autowired
    public FolderService(FolderRepository folderRepository, CategoryService categoryService) {
        this.folderRepository = folderRepository;
        this.categoryService = categoryService;
    }

    public Folder create(FolderForm folderForm) {
        Folder folder = new Folder(folderForm.getName().trim(), folderForm.getDescription().trim());
        folder.setCategory(categoryService.findByName(folderForm.getCategory()));
        return save(folder);
    }

    public Folder update(Folder oldFolder, FolderForm folderForm) {
        Category category = categoryService.findByName(folderForm.getCategory());
        oldFolder.setName(folderForm.getName());
        oldFolder.setDescription(folderForm.getDescription());
        oldFolder.setCategory(category);
        return save(oldFolder);
    }

    public Page<Folder> getPageableCategories(Pageable pageable) {
        return folderRepository.findAll(pageable);
    }

    public Folder findOne(Long id) {
        return folderRepository.findOne(id);
    }

    public List<Folder> findAll() {
        return folderRepository.findAll();
    }

    public Folder findByName(String name) {
        return folderRepository.findByName(name);
    }

    public Folder save(Folder folder) {
        return folderRepository.save(folder);
    }

    public long count() {
        return folderRepository.count();
    }

    public void delete(Long id) {
        folderRepository.delete(id);
    }
}
