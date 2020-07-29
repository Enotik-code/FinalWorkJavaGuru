package by.edabudet.controller.user;

import by.edabudet.authentication.bean.User;
import by.edabudet.authentication.repository.UserRepository;
import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.authentication.service.UserService;
import by.edabudet.strings.SuccessConstants;
import by.edabudet.validate.DatePattern;
import by.edabudet.validate.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class UserInfoController {

    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/additionalInfo")
    public ModelAndView viewHomePage(){
        ModelAndView mod = new ModelAndView("user/userData");
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        User user =  userService.findUserByUserName(userService.getCurrentUsername());
        mod.addObject("user", user);
        return mod;
    }

    @PostMapping(value = "/additionalInfo")
    public ModelAndView updateInfo(@RequestParam(value = "input_email", required = false) String userEmail,
                                   @RequestParam(value = "input_user_name", required = false) String userName,
                                   @RequestParam(value = "input_first_name", required = false) String firstName,
                                   @RequestParam(value = "input_second_name", required = false) String secondName,
                                   @RequestParam(value = "input_patronymic", required = false) String patronymic,
                                   @RequestParam(value = "input_address", required = false) String address,
                                   @RequestParam(value = "input_birthday", required = false) Date birthday,
                                   @RequestParam(value = "input_gender", required = false) String gender){
        ModelAndView mod = new ModelAndView("user/userData");
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        User userFromDB = userService.findUserByUserName(userService.getCurrentUsername());
        if (userFromDB != null) {
            mod.addObject("userNameMessage", "User with the same name already exists!");
            return mod;
        }
        if (!UserValidate.validateFirstName(firstName)) {
            mod.addObject("firstNameMessage", "Enter a name of more than 3 characters.");
        }
        if (!UserValidate.validateLastName(secondName)) {
            mod.addObject("lastNameMessage", "Enter the last name more than 3 characters.");
        }
        User user = User.builder()
                .dateOfModified(DatePattern.getCurrentDate())
                .email(userEmail)
                .userName(userName)
                .firstName(firstName)
                .lastName(secondName)
                .patronymic(patronymic)
                .address(address)
                .dateOfBirthday(birthday)
                .gender(gender)
                .build();
        userService.updateUserInfo(user,userService.getCurrentUsername());
        return mod;
    }
}
