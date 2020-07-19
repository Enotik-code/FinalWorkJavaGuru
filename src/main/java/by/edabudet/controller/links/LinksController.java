package by.edabudet.controller.links;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinksController {

    @GetMapping(value = "/links")
    public String linksPage(){
        return "links/links";
    }
}
