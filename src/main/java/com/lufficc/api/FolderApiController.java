package com.lufficc.api;

import com.lufficc.api.exception.NotFoundException;
import com.lufficc.api.model.JsonWrap;
import com.lufficc.api.model.PagedJson;
import com.lufficc.model.Article;
import com.lufficc.model.Folder;
import com.lufficc.service.ArticleService;
import com.lufficc.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
@RestController
@Transactional
@RequestMapping("api/folder")
public class FolderApiController extends BaseApiController {

    private final FolderService folderService;

    private final ArticleService articleService;

    @Autowired
    public FolderApiController(FolderService folderService, ArticleService articleService) {
        this.folderService = folderService;
        this.articleService = articleService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public PagedJson<List<Folder>> getFolders(Pageable pageable) {
        Page<Folder> folders = folderService.getPageableCategories(pageable);
        PagedJson<List<Folder>> pagedJson = new PagedJson<>(HttpStatus.OK.value(), "success", folders.getContent());
        pagedJson.fillData(folders);
        return pagedJson;
    }

    @RequestMapping(value = "/{id:[0-9]+]}", method = RequestMethod.GET)
    public JsonWrap<Folder> getFolder(@PathVariable("id") Long id) throws NotFoundException {
        Folder folder = folderService.findOne(id);
        if (folder == null)
            throw new NotFoundException("article with id " + id + " not fount");
        return new JsonWrap<>(HttpStatus.OK.value(), "success", folder);
    }

    @RequestMapping(value = "/{folder_id:[0-9+]/article}", method = RequestMethod.GET)
    public JsonWrap<List<Article>> getArticlesByFolder(@PathVariable("folder_id") Long folder_id) {
        return new JsonWrap<>(HttpStatus.OK.value(), "success", articleService.getArticleByFolder(folder_id));
    }
}
