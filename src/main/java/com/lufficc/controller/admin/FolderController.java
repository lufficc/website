package com.lufficc.controller.admin;

import com.lufficc.api.exception.NotFoundException;
import com.lufficc.controller.BaseController;
import com.lufficc.model.Folder;
import com.lufficc.model.form.FolderForm;
import com.lufficc.service.CategoryService;
import com.lufficc.service.FolderService;
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
@RequestMapping("admin/folder")
public class FolderController extends BaseController {
    private static final String BASE_REDIRECT_URL = "redirect:/admin/folder";

    private final FolderService folderService;

    private final CategoryService categoryService;

    @Autowired
    public FolderController(FolderService folderService, CategoryService categoryService) {
        this.folderService = folderService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("folders", folderService.findAll());
        return "admin/folder/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(FolderForm folderForm, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/folder/create";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String doCreate(@Valid FolderForm folderForm, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("folderForm", folderForm);
            danger(attributes, "标*的不能为空");
            return BASE_REDIRECT_URL + "/create";
        }
        if (folderService.findByName(folderForm.getName()) != null) {
            attributes.addFlashAttribute("folderForm", folderForm);
            warning(attributes, folderForm.getName() + "已经存在");
            return BASE_REDIRECT_URL + "/create";
        }
        folderService.create(folderForm);

        success(attributes, "文件夹" + folderForm.getName() + "创建成功");
        return BASE_REDIRECT_URL;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String doDelete(@RequestParam("id") long id, RedirectAttributes attributes) {
        folderService.delete(id);
        success(attributes, "删除成功");
        return BASE_REDIRECT_URL;
    }

    @RequestMapping(value = "update/{id:[0-9]+}", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id, Model model) throws NotFoundException {
        Folder folder = folderService.findOne(id);
        Utils.checkExists(folder);
        model.addAttribute("folderForm", FolderForm.fromFolder(folder));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("id", id);
        return "admin/folder/update";
    }

    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.PUT)
    public String doUpdate(@PathVariable("id") Long id,
                           @Valid FolderForm folderForm,
                           BindingResult result,
                           RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("folderForm", folderForm);
            danger(attributes, "标*的不能为空");
            return BASE_REDIRECT_URL + "/update/" + id;
        }
        Folder folder = folderService.findOne(id);
        if (!Objects.equals(folder.getName(), folderForm.getName()) && folderService.findByName(folderForm.getName().trim()) != null) {
            warning(attributes, folderForm.getName() + "已经存在");
            return BASE_REDIRECT_URL + "/update/" + id;
        }
        folderService.update(folder, folderForm);

        success(attributes, "文件夹" + folderForm.getName() + "修改成功");
        return BASE_REDIRECT_URL;
    }
}
