package by.edabudet.admin.controller;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private UserAccessService userAccessService;

    @GetMapping(value = "admin/home")
    public ModelAndView adminPage(){
        ModelAndView modelAndView = new ModelAndView("admin/home");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        return modelAndView;
    }
}
