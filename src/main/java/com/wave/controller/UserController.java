package com.wave.controller;

import com.wave.dao.UserDAO;
import com.wave.model.User;
import com.wave.staticsetting.ContextUtil;
import com.wave.staticsetting.ReturnStatus;
import com.wave.staticsetting.StaticConfig;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Json on 2016/12/6.
 */
@RestController
public class UserController {
    private UserDAO userDAO= (UserDAO) ContextUtil.getBean("UserDAO");

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public HashMap<String,Integer> register(@RequestParam(value = "name")String name,
                            @RequestParam(value = "first_name")String f_n,
                            @RequestParam(value = "last_name")String l_n,
                            @RequestParam(value = "password")String password,
                            @RequestParam(value = "age",defaultValue = "0")int age,
                            @RequestParam(value = "sex",defaultValue = "0")int sex,
                            @RequestParam(value = "phone_number")String p_n,
                            @RequestParam(value = "email")String email){

        //salt encode
        String salt=getSalt(20);
        password=new StandardPasswordEncoder(salt).encode(password);
        System.out.println(password.length());

        User user=new User();
        user.setNickname(name);
        user.setFirst_name(f_n);
        user.setLast_name(l_n);
        user.setSalt(salt);
        user.setPassword(password);
        user.setAge(age);
        user.setPhone_number(p_n);
        user.setEmail(email);
        user.setSex(sex);
        user.setPortrait_url(StaticConfig.DEFAULT_PORTRAIT_URL);
        try{
            userDAO.save(user);
            return ReturnStatus.getReturn(ReturnStatus.REGISTER_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnStatus.getReturn(ReturnStatus.REGISTER_FAIL);
        }
    }

    @RequestMapping(value = "/user/login")
    public HashMap<String,Integer> login(@RequestParam(value = "phone_number",defaultValue = "")String phone_number,
                                         @RequestParam(value = "email",defaultValue = "")String email,
                                         @RequestParam(value = "password")String password){
        User user=null;
        if(!phone_number.equals("")){
            user=userDAO.getByPhone_Number(phone_number);
        }
        if(user==null&&!email.equals("")){
            user=userDAO.getByEmail(email);
        }

        if(user==null){
            return ReturnStatus.getReturn(ReturnStatus.LOGIN_USER_NONEXIST);
        }
        if(new StandardPasswordEncoder(user.getSalt()).matches(password,user.getPassword())){
            return ReturnStatus.getReturn(ReturnStatus.LOGIN_SUCCESS);
        }
        else return ReturnStatus.getReturn(ReturnStatus.LOGIN_PASSERROR);
    }

    private String getSalt(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
