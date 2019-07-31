package com.example.project.controller;

import com.example.project.entity.Response;
import com.example.project.entity.User;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@EnableAutoConfiguration
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "demo";
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public Response loginCheck(@RequestParam("userName") String userName, @RequestParam("password") String password) throws IOException {
        Response response = new Response();
        User user = new User();
        user.setNick(userName);
        user.setPassword(password);
        boolean isValidUser;
        try {
            isValidUser = userService.hasMatchUser(user.getNick(), user.getPassword());
            if (!isValidUser) {
                response.failure(-1, "not exist");
            } else {
                user = userService.findUserByUserName(user.getNick());
                response.success(user);
            }
        } catch (Exception exception) {
            response.failure(-1, exception.getMessage());
        }
        return response;
    }
}
