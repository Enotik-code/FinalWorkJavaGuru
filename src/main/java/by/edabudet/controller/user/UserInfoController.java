package by.edabudet.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserInfoController {

    @GetMapping("/additionalInfo")
    public String enterAdditionalInfo(){
        return "user/userData";
    }
}
