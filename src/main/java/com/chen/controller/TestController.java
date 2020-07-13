package com.chen.controller;

import com.chen.entity.User;
import com.jfinal.core.Controller;

import java.util.ArrayList;
import java.util.List;

public class TestController extends Controller {
    /***
     * 测试 #（value）
     */
    public void test1(){
        set("value","chenxc");
        render("test.html");
    }

    /**
     * 测试#(object.field)
     */
    public void test2(){
        User user = new User();
        user.setUserName("sdfa");
        user.setPassWord("123");
        set("user",user);
        render("test.html");
    }


    /**
     *
     * 测试#for(l ： List)  #end
     *
     * 遍历集合：
     * 集合里面时普通数据（String int ...）直接写  l
     * 集合里面是对象 l.字段名
     */
    public void test3(){
        List<String> list = new ArrayList<>();
        for(int i=0;i<=10;i++){
            list.add(i+"");
        }
        set("list",list);
        render("test.html");
    }

    /**
     * 二维码测试
     */
}
