package com.wave.staticsetting;

import java.util.HashMap;

/**
 * Created by Json on 2016/12/6.
 */
public class ReturnStatus {
    public static HashMap<String,Integer> getReturn(int status){
        HashMap<String,Integer> map=new HashMap<String, Integer>();
        map.put("status",status);
        return map;
    }

    public static int REGISTER_SUCCESS=0;
    public static int REGISTER_FAIL=1;

    public static int LOGIN_SUCCESS=0;
    public static int LOGIN_DATA_MISSING=1;
    public static int LOGIN_USER_NONEXIST=2;
    public static int LOGIN_PASSERROR=3;

    public static int LOGOUT_SUCCESS=0;
}
