package by.edabudet.controller.user;

import by.edabudet.authentication.bean.User;
import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.authentication.service.UserService;
import by.edabudet.strings.SuccessConstants;
import by.edabudet.validate.DatePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class UserInfoController {

    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/additionalInfo")
    public ModelAndView updateUserInfo(){
        ModelAndView modelAndView = new ModelAndView("user/userData");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        modelAndView.addObject("user", userService.findUserByUserName(userService.getCurrentUsername()));
        return modelAndView;
    }

    @PostMapping(value = "/additionalInfo")
    public ModelAndView updateInfo(@RequestParam(value = "input_email", required = false) String userEmail,
                                   @RequestParam(value = "input_user_name", required = false) String userName,
                                   @RequestParam(value = "input_first_name", required = false) String firstName,
                                   @RequestParam(value = "input_second_name", required = false) String secondName,
                                   @RequestParam(value = "input_patronymic", required = false) String patronymic,
                                   @RequestParam(value = "input_address", required = false) String address,
                                   @RequestParam(value = "input_birthday", required = false) String birthday,
                                   @RequestParam(value = "input_gender", required = false) String gender) throws ParseException {
        ModelAndView mod = new ModelAndView("user/userData");
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject("user", userService.findUserByUserName(userService.getCurrentUsername()));
        User userFromDB = userService.findUserByUserName(userService.getCurrentUsername());
        User user = User.builder()
                .id(userFromDB.getId())
                .dateOfModified(DatePattern.getCurrentDate())
                .email(userEmail)
                .userName(userName)
                .firstName(firstName)
                .lastName(secondName)
                .patronymic(patronymic)
                .address(address)
                //.dateOfBirthday(Date.valueOf(birthday))
                .gender(gender)
                .build();
        userService.updateUserInfo(user);
        return new ModelAndView("redirect:/additionalInfo");
    }
}
