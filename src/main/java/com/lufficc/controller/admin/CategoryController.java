package com.lufficc.controller.admin;

import com.lufficc.api.exception.NotFoundException;
import com.lufficc.controller.BaseController;
import com.lufficc.model.Category;
import com.lufficc.model.form.CategoryForm;
import com.lufficc.service.CategoryService;
import com.lufficc.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
@Controller
@Transactional
@RequestMapping("admin/category")
public class CategoryController extends BaseController {
    private static final String BASE_REDIRECT_URL = "redirect:/admin/category";

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/category/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(CategoryForm categoryForm) {
        return "admin/category/create";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String doCreate(@Valid CategoryForm categoryForm, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("categoryForm", categoryForm);
            danger(attributes, "标*的不能为空");
            return BASE_REDIRECT_URL + "/create";
        }
        if (categoryService.findByName(categoryForm.getName().trim()) != null) {
            attributes.addFlashAttribute("categoryForm", categoryForm);
            warning(attributes, categoryForm.getName() + "已经存在");
            return BASE_REDIRECT_URL + "/create";
        }
        categoryService.create(categoryForm);
        success(attributes, "分类" + categoryForm.getName() + "创建成功");
        return BASE_REDIRECT_URL;
    }


    @RequestMapping(value = "update/{id:[0-9]+}", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) throws NotFoundException {
        Category category = categoryService.findOne(id);
        Utils.checkExists(category);
        model.addAttribute("categoryForm", category);
        model.addAttribute("id", id);
        return "admin/category/update";
    }

    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.PUT)
    public String doUpdate(@PathVariable("id") Long id,
                           @Valid CategoryForm categoryForm,
                           BindingResult result,
                           RedirectAttributes attributes) {
        if (result.hasErrors()) {
            danger(attributes, "标*的不能为空");
            return BASE_REDIRECT_URL + "/update/" + id;
        }
        Category category = categoryService.findOne(id);
        if (!Objects.equals(category.getName(), categoryForm.getName()) && categoryService.findByName(categoryForm.getName().trim()) != null) {
            warning(attributes, categoryForm.getName() + "已经存在");
            return BASE_REDIRECT_URL + "/update/" + id;
        }
        categoryService.update(category, categoryForm);
        success(attributes, "分类" + categoryForm.getName() + "修改成功");
        return BASE_REDIRECT_URL;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String doDelete(@RequestParam("id") long id, RedirectAttributes attributes) {
        categoryService.delete(id);
        success(attributes, "删除成功");
        return BASE_REDIRECT_URL;
    }
}
