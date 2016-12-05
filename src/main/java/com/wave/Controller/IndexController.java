package com.wave.controller;

import com.wave.dao.ContextUtil;
import com.wave.dao.UserDAO;
import com.wave.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Json on 2016/10/8.
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/index")
    public String index(){
        System.out.println("get index");
        return "content";
    }

    @RequestMapping(value = "/graph")
    public String graph(){
        return "graph";
    }


    @RequestMapping(value = "/user")
    public @ResponseBody
    User user(){
        User user=new User();
        user.setAccount_balance(10000);
        user.setNickname("GAYSON");
        user.setFirst_name("Ji");
        user.setLast_name("Xunzhen");
        user.setPhone_number("18221026671");
        user.setAge(20);
        user.setEmail("playagayson@gamil.com");
        user.setPortrait_url("null");
        UserDAO userDAO= (UserDAO) ContextUtil.getBean("UserDAO");
        userDAO.save(user);


        return user;
    }
}
