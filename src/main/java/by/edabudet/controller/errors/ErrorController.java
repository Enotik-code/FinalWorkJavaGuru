package by.edabudet.controller.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @RequestMapping("/403")
    public ModelAndView accessDenied() {
        ModelAndView mov = new ModelAndView();
        mov.setViewName("errors/403");
        return mov;
    }

    @RequestMapping("/404")
    public ModelAndView pageNotFound() {
        ModelAndView mov = new ModelAndView();
        mov.setViewName("errors/404");
        return mov;
    }

}
