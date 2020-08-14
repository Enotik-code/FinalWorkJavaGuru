package by.edabudet.admin.controller;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.bean.Product;
import by.edabudet.persistence.dao.repositories.ProductRepository;
import by.edabudet.persistence.dao.servises.implementations.ManufacturerSimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.ProductSimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.SubcategorySimpleServiceImpl;
import by.edabudet.strings.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class AdminProductController {

    @Autowired
    private SubcategorySimpleServiceImpl subcategorySimpleService;

    @Autowired
    private ManufacturerSimpleServiceImpl manufacturerSimpleService;

    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private ProductSimpleServiceImpl productSimpleService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/admin/product")
    public ModelAndView adminProductPage() throws SQLException {
        ModelAndView modelAndView = new ModelAndView( "admin/product/startPage");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        modelAndView.addObject(EntityConstant.PRODUCTS, productSimpleService.findAll());
        return modelAndView;
    }

    @PostMapping(value =  Http.SORTING_NAME_ADMIN)
    public String sortingProductsByNameAdmin(@ModelAttribute("SortByName") String variable, Model model) throws SQLException {
        List<Product> productList = productSimpleService.findAll();
        if (variable.equals(SortingOptions.ORDER_NAME)) {
            productList.sort(Comparator.comparing(Product::getName));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_NAME)) {
            productList.sort(Comparator.comparing(Product::getName, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        return "/admin/product/startPage";
    }

    @PostMapping(value = Http.SORTING_PRICE_ADMIN)
    public String sortingProductsByPriceAdmin(@ModelAttribute("SortByPrice") String variable, Model model) throws SQLException {
        List<Product> productList = productSimpleService.findAll();
        if (variable.equals(SortingOptions.ORDER_PRICE)) {
            productList.sort(Comparator.comparing(Product::getPrice));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_PRICE)){
            productList.sort(Comparator.comparing(Product::getPrice, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, subcategorySimpleService.findAll());
        return "/admin/product/startPage";
    }

    @PostMapping(value = Http.SORTING_CATEGORY_ADMIN)
    public String sortingProductsByCategoryAdmin(@ModelAttribute("SortByCategory") String variable, Model model) throws SQLException {
        List<Product> productList = productSimpleService.findAll();
        if (variable.equals(SortingOptions.ORDER_CATEGORY)) {
            productList.sort(Comparator.comparing(Product::getSubcategory));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_CATEGORY)){
            productList.sort(Comparator.comparing(Product::getSubcategory, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, subcategorySimpleService.findAll());
        return "/admin/product/startPage";

    }

    @PostMapping(value =  Http.SORTING_DISCOUNT_ADMIN)
    public String sortingProductsByDiscountAdmin(@ModelAttribute("SortByDiscount") String variable, Model model) throws SQLException {
        List<Product> productList = productSimpleService.findAll();
        if (variable.equals(SortingOptions.ORDER_DISCOUNT)) {
            productList.sort(Comparator.comparing(Product::getDiscount));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_DISCOUNT)){
            productList.sort(Comparator.comparing(Product::getDiscount, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, subcategorySimpleService.findAll());
        return "/admin/product/startPage";

    }

    @GetMapping(value = "/admin/product/add")
    public ModelAndView adminAddProduct() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("/admin/product/crud/addProduct");
        modelAndView.addObject(EntityConstant.CATEGORIES, subcategorySimpleService.findAll());
        modelAndView.addObject(EntityConstant.MANUFACTURERS, manufacturerSimpleService.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/deleteProduct/" + "/{id}")
    public ModelAndView adminDeleteProduct(@PathVariable(name = "id") Integer id) throws SQLException {
        productRepository.deleteProductById(id);
        return new ModelAndView("redirect:/admin/product/");
    }

    @GetMapping(value = "/admin/product/update")
    public String adminUpdateProductInfo(){
        return "admin/product/crud/updateProduct";
    }

    @PostMapping(value = "/admin/product/add")
    public ModelAndView adminAddProductPost(@RequestParam(value = "name", required = false) String productName,
                                            @RequestParam(value = "price", required = false) Long price,
                                            @RequestParam(value = "discount", required = false) Long discount,
                                            @RequestParam(value = "amount", required = false) Long amount,
                                            @RequestParam(value = "subcategory", required = false) String subcategory ,
                                            @RequestParam(value = "manufacturer", required = false) String manufacturer) throws SQLException {
        int manufacturerId =  manufacturerSimpleService.getByName(manufacturer).getId();
        int subcategoryId = subcategorySimpleService.getByName(subcategory).getId();
        Product product = new Product(productName, price, discount, amount, subcategoryId, manufacturerId);
        productRepository.save(product);
        ModelAndView modelAndView = new ModelAndView("/admin/home");
        return modelAndView;
    }

}
