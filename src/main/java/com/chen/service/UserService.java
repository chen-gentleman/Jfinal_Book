package com.chen.service;

import com.chen.entity.Book;
import com.chen.entity.User;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

public class UserService {

    /**
     * 登录验证
     */
    public User user_verification(String userName,String passWord){
       // List<String> titleList = Db.query("select title from blog");
        List<User> users = new User().dao().find("select id,userName,passWord from user where (userName = ? and passWord = ?)",userName,passWord);

        if(users.size() != 0){
            return users.get(0);
        }
            return null;
    }


    //注册用户名是否重复
    public Boolean userNameisExists(String userName){
        String name = Db.queryStr("select userName from user where userName = ?", userName);
        if(name == null){
            return false;
        }
        return true;
    }

    /**
     * 用户的借书记录
     */
    public Page<Book> books(String user_id){
      //  String sql = "select b.* from book b inner join user u on b.id=u.borrow_book_id where u.id=?";
        Page<Book> books = new Book().dao().paginate(1,10,"SELECT b.* ","FROM borrow bor LEFT JOIN  USER u ON u.id = bor.user_id LEFT JOIN  book b ON  bor.book_id=b.id WHERE u.id = ?",user_id);

        return books;
    }
}
