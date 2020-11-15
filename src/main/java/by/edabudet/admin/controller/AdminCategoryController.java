package by.edabudet.admin.controller;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.bean.Subcategory;
import by.edabudet.persistence.dao.repositories.SubcategoryRepository;
import by.edabudet.persistence.dao.servises.implementations.CategorySimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.SubcategorySimpleServiceImpl;
import by.edabudet.strings.Pages;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class AdminCategoryController {

    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private CategorySimpleServiceImpl categorySimpleService;

    @Autowired
    private SubcategorySimpleServiceImpl subcategorySimpleService;

    @Autowired
    private SubcategoryRepository subcategoryRepository;


    @GetMapping(value = "admin/category")
    public ModelAndView categoryPage() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("admin/subcategory/subcategoryAdmin");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        modelAndView.addObject("subcategoryList", subcategorySimpleService.findAllJoinCategory());
        return modelAndView;
    }

    @PostMapping(value = "deleteSubcategory" + "/{id}")
    public ModelAndView deleteSubcategory(@PathVariable(name = "id") Long id) throws SQLException {
        subcategoryRepository.delete(id);
        return new ModelAndView(Pages.REDIRECT + "admin/category");
    }

    @GetMapping(value = "admin/category/add")
    public ModelAndView addCategory() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("admin/subcategory/addSubcategory");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        modelAndView.addObject("categoryList", categorySimpleService.findAll());
        return modelAndView;
    }

    @PostMapping(value = "admin/category/add")
    public ModelAndView addCategoryPost(@RequestParam(value = "category", required = false) String category,
                                        @RequestParam(value = "subcategory", required = false) String subcategory) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("admin/subcategory/addSubcategory");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        Integer categoryId =  categorySimpleService.getByName(category).getId();
        subcategoryRepository.save(Subcategory.builder()
                .IdCategory(categoryId)
                .nameSubcategory(subcategory)
                .build());
        return new ModelAndView("redirect:/admin/category");
    }
}
