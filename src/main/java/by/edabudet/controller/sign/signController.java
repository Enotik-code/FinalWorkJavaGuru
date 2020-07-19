package by.edabudet.controller.sign;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class signController {

    @GetMapping(value = "/signUp")
    public String signUp(){
        return "sign/signUp";
    }

    @GetMapping(value = "/signIn")
    public String signIn(){
        return "sign/signIn";
    }
}
