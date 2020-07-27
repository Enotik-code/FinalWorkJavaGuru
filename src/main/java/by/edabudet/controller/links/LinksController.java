package by.edabudet.controller.links;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class LinksController {

    @Autowired
    private UserAccessService userAccessService;


    @GetMapping(value = "/links")
    public ModelAndView linkPage(){
        ModelAndView mod = new ModelAndView("links/links");
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        return mod;
    }

}
