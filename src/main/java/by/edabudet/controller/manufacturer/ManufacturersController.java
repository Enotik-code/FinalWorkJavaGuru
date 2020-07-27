package by.edabudet.controller.manufacturer;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class ManufacturersController {

    @Autowired
    private UserAccessService userAccessService;

    @GetMapping(value = "/manufacturer")
    public ModelAndView viewHomePage(){
        ModelAndView mod = new ModelAndView("manufacturer/manufacturer");
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        return mod;
    }

}
