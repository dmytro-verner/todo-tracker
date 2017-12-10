package edu.springboot.web.controller;

import edu.springboot.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginMessage(ModelMap model){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(ModelMap model, @RequestParam String name,
                              @RequestParam String password) {

        boolean isValidUser = service.validateUser(name, password);

        if (isValidUser) {
            model.put("name", name);
            return "welcome";
        } else {
            model.put("errorMessage", "Invalid Credentials!!");
            return "login";
        }
    }
}
