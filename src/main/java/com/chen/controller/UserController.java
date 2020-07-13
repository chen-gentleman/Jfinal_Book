package com.chen.controller;

import com.chen.entity.Book;
import com.chen.entity.User;
import com.chen.service.UserService;
import com.chen.validator.UserValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.render.Render;

import java.util.List;

public class UserController extends Controller {

    @Inject
    private UserService userService;
    //用户登录
    public void user_login(){
       render("login.html");
    }

    //用户注册
    public void user_register(){
        render("register.html");
    }

    //用户借书
    public void borrow_book() {
        render("borrow_book.html");
    }

    //登录验证
    //@Before(UserValidator.class)
    public void verification(){
        String name = get("user.userName");
        String pw = get("user.passWord");
        System.out.println(name);
        User user = userService.user_verification(name,pw);
        if(user == null){
            setAttr("user_not_exists","用户不存在或密码错误");
            // 保持住所有表单域
            keepPara();
         /*//指定保持住的表单域，如： nickName、email 等等
            keepPara("nickName", "email");
            render("login.html");*/
        }else {
            setAttr("user",user);
            Page<Book> books = userService.books(user.getId().toString());
           /* if(books != null){

            }*/
           setAttr("books",books);
            render("user_detitle.html");
        }
    }

    //保存用户
    @Before(UserValidator.class)
    public void save(){
        User user =  getModel(User.class);
        //加密
        String salt = HashKit.generateSaltForSha256();
        String password = HashKit.sha256(salt+user.getPassWord());
        user.setPassWord(password);
        user.save();
        render("register_success.html");
    }



}
