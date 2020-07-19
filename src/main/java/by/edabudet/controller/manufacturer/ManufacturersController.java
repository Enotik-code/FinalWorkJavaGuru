package by.edabudet.controller.manufacturer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManufacturersController {
    @GetMapping(value = "/manufacturer")
    public String manufacturersPage(){
        return "manufacturer/manufacturer";
    }
}
