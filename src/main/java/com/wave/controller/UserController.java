package com.wave.controller;

import com.wave.model.User;
import com.wave.repository.EntityRepository.UserRepository;
import com.wave.staticsetting.ReturnStatus;
import com.wave.staticsetting.StaticConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by Json on 2016/12/5.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository user_repository;

    @RequestMapping(value = "/user/login")
    public HashMap<String,Integer> login() {
        return ReturnStatus.getReturn(ReturnStatus.LOGIN_PASSERROR);
    }

    @RequestMapping(value = "/user/login_fail")
    public HashMap<String, Integer> login_fail(){
        return ReturnStatus.getReturn(ReturnStatus.LOGIN_PASSERROR);
    }

    @RequestMapping(value = "/user/login_success")
    public HashMap<String,Integer> login_success(){
        return ReturnStatus.getReturn(ReturnStatus.LOGIN_SUCCESS);
    }

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public HashMap<String,Integer> register(@RequestParam(value = "name")String name,
                                            @RequestParam(value = "first_name")String f_n,
                                            @RequestParam(value = "last_name")String l_n,
                                            @RequestParam(value = "password")String password,
                                            @RequestParam(value = "age",defaultValue = "0")int age,
                                            @RequestParam(value = "sex",defaultValue = "0")int sex,
                                            @RequestParam(value = "phone_number")String p_n,
                                            @RequestParam(value = "email")String email){

        password=new BCryptPasswordEncoder().encode(password);
        System.out.println(password.length());

        User user=new User();
        user.setNickname(name);
        user.setFirst_name(f_n);
        user.setLast_name(l_n);
        user.setPassword(password);
        user.setAge(age);
        user.setPhone_number(p_n);
        user.setEmail(email);
        user.setSex(sex);
        user.setPortrait_url(StaticConfig.DEFAULT_PORTRAIT_URL);
        user.setEnabled(true);
        try{
            user_repository.save(user);
            return ReturnStatus.getReturn(ReturnStatus.REGISTER_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnStatus.getReturn(ReturnStatus.REGISTER_FAIL);
        }
    }

    @RequestMapping(value = "/user/logintest")
    public String logintest(){
        return "login_success";
    }

    @RequestMapping(value = "/user/logout")
    public void logout(){}

    @RequestMapping(value = "/user/logout_success")
    public HashMap<String,Integer> logout_success(){
        return ReturnStatus.getReturn(ReturnStatus.LOGOUT_SUCCESS);
    }

    @RequestMapping(value = "/user/test")
    public HashMap<String, Integer> test() {
        return ReturnStatus.getReturn(ReturnStatus.LOGIN_USER_NONEXIST);
    }
}
