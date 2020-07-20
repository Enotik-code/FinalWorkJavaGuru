package by.edabudet.controller;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.strings.EntityConstant;
import by.edabudet.strings.Http;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    private UserAccessService userAccessService;

    @GetMapping(value = "/index")
    public ModelAndView viewHomePage() throws SQLException {
        ModelAndView mod = new ModelAndView("index");
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        return mod;
    }
}
