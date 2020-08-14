package by.edabudet.admin.controller;

import by.edabudet.authentication.repository.UserRepository;
import by.edabudet.authentication.service.UserAccessService;
import by.edabudet.authentication.service.UserService;
import by.edabudet.strings.EntityConstant;
import by.edabudet.strings.Pages;
import by.edabudet.strings.SuccessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class AdminUsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccessService userAccessService;

    @GetMapping(value = "/admin/users")
    public ModelAndView getUsersList(){
        ModelAndView modelAndView = new ModelAndView("admin/user/startPage");
        modelAndView.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        modelAndView.addObject("userList", userRepository.findUserByActiveTrue());
        modelAndView.addObject("userListInactive", userRepository.findUserByActiveFalse());
        return modelAndView;
    }

    @PostMapping(value = "deleteUser" + "/{id}")
    public ModelAndView deleteUser(@PathVariable(name = "id") Long id){
        userRepository.deleteById(id);
        return new ModelAndView(Pages.REDIRECT + "admin/users");
    }


    @PostMapping(value = "deactivate" + "/{id}")
    public ModelAndView deactivateUser(@PathVariable(name = "id") Long id) throws SQLException {
        userService.deactivateUser(id);
        return new ModelAndView(Pages.REDIRECT + "admin/users");
    }


    @PostMapping(value = "activate" + "/{id}")
    public ModelAndView activateUser(@PathVariable(name = "id") Long id) throws SQLException {
        userService.activateUser(id);
        return new ModelAndView(Pages.REDIRECT + "admin/users");
    }

}
