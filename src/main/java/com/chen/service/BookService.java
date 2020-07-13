package com.chen.service;

import com.chen.entity.Book;
import com.jfinal.plugin.activerecord.Page;

/**
 * 书本service
 */
public class BookService {

    //按名字查找书本
    public Page<Book> findBookByBookName(String bookName,String pageNumber){
        if(pageNumber == null || pageNumber.equals("")){
            pageNumber = "1";
        }
        Page<Book> paginate = new Book().dao().paginate
                (Integer.parseInt(pageNumber), 2, "select *", "from book where bookName like  ? ", "%"+bookName+"%");
        return paginate;
    }

    //按ID查找书本
    public Book findBookByBookId(String id){
        Book book = new Book().dao().findById(id);
        return book;
    }

    //添加图书入库
    public void book_save(Book book){
        book.save();
    }




}
