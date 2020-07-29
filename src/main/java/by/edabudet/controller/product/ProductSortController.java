package by.edabudet.controller.product;

import by.edabudet.bean.Product;
import by.edabudet.persistence.dao.servises.implementations.ProductSimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.SubcategorySimpleServiceImpl;
import by.edabudet.strings.EntityConstant;
import by.edabudet.strings.Http;
import by.edabudet.strings.Pages;
import by.edabudet.strings.SortingOptions;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ProductSortController {

    static Logger log = Logger.getLogger(ProductSortController.class.getName());

    @Autowired
    private ProductSimpleServiceImpl productSimpleService;

    @Autowired
    private SubcategorySimpleServiceImpl subcategorySimpleService;

    @PostMapping(value = Http.SORTING_NAME)
    public String sortingProductsByName(@ModelAttribute("sortByName") String variable, Model model) throws SQLException {
        List<Product> productList = productSimpleService.findAll();
        if (variable.equals(SortingOptions.ORDER_NAME)) {
            productList.sort(Comparator.comparing(Product::getName));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_NAME)) {
            productList.sort(Comparator.comparing(Product::getName, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        return "home";
    }

    @PostMapping(value = Http.SORTING_PRICE)
    public String sortingProductsByPrice(@ModelAttribute("sortByPrice") String variable, Model model) throws SQLException {
        List<Product> productList = productSimpleService.findAll();
        if (variable.equals(SortingOptions.ORDER_PRICE)) {
            productList.sort(Comparator.comparing(Product::getPrice));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_PRICE)){
            productList.sort(Comparator.comparing(Product::getPrice, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, subcategorySimpleService.findAll());
        return Pages.HOME;
    }

    @PostMapping(value = Http.SORTING_CATEGORY)
    public String sortingProductsByCategory(@ModelAttribute("sortByCategory") String variable, Model model) throws SQLException {
        List<Product> productList = productSimpleService.findAll();
        if (variable.equals(SortingOptions.ORDER_CATEGORY)) {
            productList.sort(Comparator.comparing(Product::getSubcategory));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_CATEGORY)){
            productList.sort(Comparator.comparing(Product::getSubcategory, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, subcategorySimpleService.findAll());
        return Pages.HOME;
    }

    @PostMapping(value = Http.SORTING_DISCOUNT)
    public String sortingProductsByDiscount(@ModelAttribute("sortByDiscount") String variable, Model model) throws SQLException {
        List<Product> productList = productSimpleService.findAll();
        if (variable.equals(SortingOptions.ORDER_DISCOUNT)) {
            productList.sort(Comparator.comparing(Product::getDiscount));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_DISCOUNT)){
            productList.sort(Comparator.comparing(Product::getDiscount, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, subcategorySimpleService.findAll());
        return Pages.HOME;
    }

}
