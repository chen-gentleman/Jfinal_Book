package com.chen.controller;

import com.chen.entity.Book;
import com.chen.entity.User;
import com.chen.service.AdminService;
import com.chen.utils.BookName_util;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.sun.org.apache.bcel.internal.util.ClassPath;

import java.io.File;

public class AdminController extends Controller {
    @Inject
    private static AdminService adminService;

    //管理员登录
    public void adminLogin(){
        render("adminLogin.html");
    }

    //管理员验证
    public void isAdmin(){
        String name = get("user.userName");
        String pw = get("user.passWord");
        User user = adminService.isAdmin(name,pw);
        if(user == null){
            setAttr("user_not_exists","用户不存在或密码错误");
            render("adminLogin.html");
        }else {
            if(user.getRole() != 1){
                setAttr("is_not_admin","您不是管理员!!");
                render("adminLogin.html");
                return;
            }
            setAttr("admin",user);
            render("admin.html");
        }
    }

    //图书入库
    public void book_in(){
        UploadFile file = getFile();
        String book_pic = BookName_util.get_name(file.getFileName());
        System.out.println(book_pic);
        System.out.println(getSession().getServletContext().getRealPath("/")
                + "\\pic\\");
        //文件重命名
        file.getFile().renameTo(new File(getSession().getServletContext().getRealPath("/")
                + "\\pic\\",book_pic));
        String book_name = get("book.bookName");
        String book_author = get("book.bookAuthor");
        String book_press = get("book.bookPress");
        Book book = new Book();
        book.setBookName(book_name);
        book.setBookAuthor(book_author);
        book.setBookPress(book_press);
        book.setBookPic("/pic/"+book_pic);
        adminService.book_in(book);
        renderText("入库成功");
    }


}
