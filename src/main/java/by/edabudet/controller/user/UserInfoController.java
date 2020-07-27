package by.edabudet.controller.user;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserInfoController {

    @Autowired
    private UserAccessService userAccessService;

    @GetMapping(value = "/additionalInfo")
    public ModelAndView viewHomePage(){
        ModelAndView mod = new ModelAndView("user/userData");
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        return mod;
    }
}
