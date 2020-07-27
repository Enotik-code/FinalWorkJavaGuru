package by.edabudet.controller.sign;

import by.edabudet.authentication.bean.User;
import by.edabudet.authentication.service.UserService;
import by.edabudet.mail.MailEngine;
import by.edabudet.validate.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static by.edabudet.validate.DatePattern.getCurrentDate;
import static java.time.LocalDate.*;

@Controller
public class SignController {

    @Autowired
    private UserService userService;

    @Autowired
    MailEngine mailEngine;

    @GetMapping(value = "/signUp")
    public String signUp(){
        return "sign/signUp";
    }

    @PostMapping(value = "/signIn")
    public ModelAndView signIn(){
        return new ModelAndView("/sign/signIn");
    }

    @PostMapping(value = "/signUp")
    public ModelAndView addNewUser(@RequestParam(value = "user_name", required = false) String userName,
                                   @RequestParam(value = "first_name", required = false) String firstName,
                                   @RequestParam(value = "last_name", required = false) String lastName,
                                   @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "password", required = false) String password,
                                   @RequestParam Optional<String> confPassword) {
        ModelAndView mod = new ModelAndView("sign/signUp");
        User userFromDb = userService.findUserByUserName(userName);
        User newUser = User.builder()
                .userName(userName)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .activationCode(userService.generateActivationCode())
                .password(password)
                .dateOfCreated(getCurrentDate())
                .dateOfModified(getCurrentDate())
                .build();
        if (userFromDb != null) {
            mod.addObject("userNameMessage", "User with the same name already exists!");
            return mod;
        }
        if (!confPassword.isPresent() || !newUser.getPassword().equals(confPassword.get())) {
            mod.addObject("confPasswordMessage", "Password mismatch.");
        }
        if (!UserValidate.validateFirstName(newUser.getFirstName())) {
            mod.addObject("firstNameMessage", "Enter a name of more than 3 characters.");
        }
        if (!UserValidate.validateLastName(newUser.getLastName())) {
            mod.addObject("lastNameMessage", "Enter the last name more than 3 characters.");
        }
        if (!UserValidate.validatePassword(newUser.getPassword())) {
            mod.addObject("passwordMessage", "Create a password with more than 8 characters.");
        }
        if (UserValidate.checkValidateDataUser(newUser) &&
                (!confPassword.isPresent() || newUser.getPassword().equals(confPassword.get()))) {
            userService.saveUser(newUser, Optional.empty());
            mod.addObject("successRegistration", "User registered successfully!");
            mod.addObject("user", new User());
            mod.setViewName("/signUp");
            return new ModelAndView("redirect:/activation");
        }
        return mod;
    }

    @GetMapping("/activation")
    public ModelAndView openEditor(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign/activation");
        return modelAndView;
    }

    @PostMapping ("/activation")
    public ModelAndView SendActivationCode(User user,@RequestParam (value = "code")String code) throws MessagingException {
        ModelAndView mod = new ModelAndView();
        mailEngine.sendHTMLTestEmailWithLogo(user);
         if( userService.findUserByEmail(user.getEmail()).getActivationCode().equals(code))
             return new ModelAndView("redirect:/additionalInfo");
         else {
             mod.addObject("errorCode", "Wrong code!");
         }
         return mod;
    }
}
