package by.edabudet.admin.controller;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.bean.Manufacturer;
import by.edabudet.persistence.dao.repositories.SubcategoryRepository;
import by.edabudet.persistence.dao.servises.implementations.CategorySimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.ManufacturerSimpleServiceImpl;
import by.edabudet.persistence.dao.servises.implementations.SubcategorySimpleServiceImpl;
import by.edabudet.strings.Pages;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class AdminManufacturerController {
    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private ManufacturerSimpleServiceImpl manufacturerSimpleService;


    @GetMapping(value = "admin/manufacturer")
    public ModelAndView categoryPage() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("/admin/manufacturer/manufacturersAdmin");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        modelAndView.addObject("manufacturerList", manufacturerSimpleService.findAll());
        return modelAndView;
    }

    @PostMapping(value = "deleteManufacturer" + "/{id}")
    public ModelAndView activateUser(@PathVariable(name = "id") Long id) throws SQLException {
        manufacturerSimpleService.delete(id);
        return new ModelAndView(Pages.REDIRECT + "admin/manufacturer");
    }

    @PostMapping(value = "/admin/manufacturer/add")
    public ModelAndView adminAddProductPost(@RequestParam(value = "name", required = false) String name) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("/admin/manufacturer/addManufacturer");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        manufacturerSimpleService.save(Manufacturer.builder().name(name).build());
        return new ModelAndView("redirect:/admin/manufacturer");
    }

    @GetMapping(value = "/admin/manufacturer/add")
    public ModelAndView addManufacturer() {
        ModelAndView modelAndView = new ModelAndView("/admin/manufacturer/addManufacturer");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        return modelAndView;
    }
}


