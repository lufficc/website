package com.lufficc.api;

import com.lufficc.api.exception.NotFoundException;
import com.lufficc.api.model.JsonWrap;
import com.lufficc.api.model.PagedJson;
import com.lufficc.model.Category;
import com.lufficc.service.CategoryService;
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
@RequestMapping("api/category")
public class CategoryApiController extends BaseApiController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public PagedJson<List<Category>> getCategories(Pageable pageable) {
        Page<Category> categories = categoryService.getPageableCategories(pageable);
        PagedJson<List<Category>> pagedJson = new PagedJson<>(HttpStatus.OK.value(), "success", categories.getContent());
        pagedJson.fillData(categories);
        return pagedJson;
    }

    @RequestMapping(value = "/{id:[0-9]+]}", method = RequestMethod.GET)
    public JsonWrap<Category> getCategory(@PathVariable("id") Long id) throws NotFoundException {
        Category category = categoryService.findOne(id);
        if (category == null)
            throw new NotFoundException("article with id " + id + " not fount");
        return new JsonWrap<>(HttpStatus.OK.value(), "success", category);
    }
}
