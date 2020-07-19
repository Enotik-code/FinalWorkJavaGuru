package by.edabudet.controller.product;

import by.edabudet.bean.Manufacturer;
import by.edabudet.bean.Product;
import by.edabudet.persistence.dao.servises.implementations.CategorySimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.ManufacturerSimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.ProductSimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.SubcategorySimpleServiceImpl;
import by.edabudet.strings.EntityConstant;
import by.edabudet.strings.Http;
import by.edabudet.strings.Pages;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/product")
@Controller
public class ProductController{

    static Logger log = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private CategorySimpleServiceImpl categorySimpleService;

    @Autowired
    private ProductSimpleServiceImpl productSimpleService;

    @Autowired
    private SubcategorySimpleServiceImpl subcategorySimpleService;

    @Autowired
    private ManufacturerSimpleServiceImpl manufacturerSimpleService;

    @GetMapping(value = Http.HOME)
    public ModelAndView viewHomePage() throws SQLException {
        ModelAndView mod = new ModelAndView("home");
        //mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.PRODUCTS, productSimpleService.findAll());
        mod.addObject(EntityConstant.CATEGORIES, categorySimpleService.findAll());
        mod.addObject(EntityConstant.MANUFACTURERS, manufacturerSimpleService.findAll());
        return mod;
    }

    @PostMapping(value = Http.CANCEL)
    public String clearFilterAndSearch() {
        return Pages.REDIRECT + Pages.HOME;
    }

    @GetMapping(value = Http.SEARCH)
    public String searchProductByName(@ModelAttribute(EntityConstant.ENTITY_NAME_PRODUCT) String name, Model model) throws SQLException {
        Product product = productSimpleService.getByName(name);
        //model.addAttribute(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        model.addAttribute(EntityConstant.PRODUCTS, product);
        return Pages.HOME;
    }

}
