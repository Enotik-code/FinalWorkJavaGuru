package by.edabudet.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "admin/home")
    public String adminPage(){
        return "admin/home";
    }
}
