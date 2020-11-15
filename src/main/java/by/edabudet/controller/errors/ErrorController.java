package by.edabudet.controller.errors;

import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @Autowired
    private UserAccessService userAccessService;

    @RequestMapping("/403")
    public ModelAndView accessDenied() {
        ModelAndView mov = new ModelAndView();
        mov.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mov.setViewName("errors/403");
        return mov;
    }

    @RequestMapping("/404")
    public ModelAndView pageNotFound() {
        ModelAndView mov = new ModelAndView();
        mov.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mov.setViewName("errors/404");
        return mov;
    }

}
