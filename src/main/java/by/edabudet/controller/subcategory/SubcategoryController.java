package by.edabudet.controller.subcategory;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.bean.Product;
import by.edabudet.bean.Subcategory;
import by.edabudet.persistence.dao.servises.implementations.SubcategorySimpleServiceImpl;
import by.edabudet.strings.EntityConstant;
import by.edabudet.strings.Http;
import by.edabudet.strings.Pages;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RequestMapping("/category")
@Controller
public class SubcategoryController {

    @Autowired
    private SubcategorySimpleServiceImpl subcategorySimpleService;

    @Autowired
    private UserAccessService userAccessService;

    @GetMapping(value = "subcategory")
    public ModelAndView viewSubcategoryPage() throws SQLException {
        ModelAndView mod = new ModelAndView("subcategory/subcategory");
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.SUBCATEGORIES_1, subcategorySimpleService.findAllFirstPart());
        mod.addObject(EntityConstant.SUBCATEGORIES_2, subcategorySimpleService.findAllSecondPart());
        return mod;
    }

    @GetMapping(value = Http.SEARCH)
    public String searchProductByName(@ModelAttribute(EntityConstant.ENTITY_NAME_PRODUCT) String name, Model model) throws SQLException {
        Subcategory subcategory = subcategorySimpleService.getByName(name);
        model.addAttribute(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        model.addAttribute(EntityConstant.SUBCATEGORIES_1, subcategory);
        return "subcategory/subcategory";
    }

}
