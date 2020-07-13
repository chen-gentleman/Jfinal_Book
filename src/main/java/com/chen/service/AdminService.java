package com.chen.service;

import com.chen.entity.Book;
import com.chen.entity.User;
import com.jfinal.aop.Inject;

import java.util.List;

public class AdminService {

    @Inject
    private static BookService bookService;

    //验证是否是管理员
    public User isAdmin(String adminName,String passWord){
        List<User> user = new User().dao().find("select id,userName,passWord,role from user where (userName = ? and passWord = ?)", adminName, passWord);
       if(user.size() != 0){
           return user.get(0);
       }
        return null;
    }

    //图书入库
    public void book_in(Book book){
        bookService.book_save(book);
    }
}
