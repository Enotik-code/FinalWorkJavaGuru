package by.edabudet.admin.controller;

import by.edabudet.persistence.dao.servises.implementations.ManufacturerSimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.SubcategorySimpleServiceImpl;
import by.edabudet.strings.EntityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class AdminProductController {

    @Autowired
    private SubcategorySimpleServiceImpl subcategorySimpleService;

    @Autowired
    private ManufacturerSimpleServiceImpl manufacturerSimpleService;

    @GetMapping(value = "/admin/product")
    public String adminProductPage(){
        return "admin/product/startPage";
    }

    @GetMapping(value = "/admin/product/add")
    public ModelAndView adminAddProduct() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("/admin/product/crud/addProduct");
        modelAndView.addObject(EntityConstant.CATEGORIES, subcategorySimpleService.findAll());
        modelAndView.addObject(EntityConstant.MANUFACTURERS, manufacturerSimpleService.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/admin/product/delete")
    public String adminDeleteProduct(){
        return "admin/product/crud/deleteProduct";
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
        ModelAndView modelAndView = new ModelAndView("/admin/product/crud/addProduct");
        modelAndView.addObject(EntityConstant.CATEGORIES, subcategorySimpleService.findAll());
        modelAndView.addObject(EntityConstant.MANUFACTURERS, manufacturerSimpleService.findAll());
        return modelAndView;
    }

}
