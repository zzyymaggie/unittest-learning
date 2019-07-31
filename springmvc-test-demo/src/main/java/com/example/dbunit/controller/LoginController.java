package com.example.dbunit.controller;

import com.example.dbunit.entity.Response;
import com.example.dbunit.entity.User;
import com.example.dbunit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "demo";
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public Response loginCheck(HttpServletResponse httpResponse, @RequestParam("userName") String userName, @RequestParam("password") String password) throws IOException {
        Response response = new Response();
        User user = new User();
        user.setNick(userName);
        user.setPassword(password);
        boolean isValidUser;
        try {
            isValidUser = userService.hasMatchUser(user.getNick(), user.getPassword());
            if(!isValidUser) {
                response.failure(-1, "not exist");
            }else {
                user = userService.findUserByUserName(user.getNick());
                response.success(user);
            }
        }catch (Exception exception) {
            response.failure(-1, exception.getMessage());
        }
        httpResponse.setContentType("application/json;charset=utf-8");
        return response;
    }
}
