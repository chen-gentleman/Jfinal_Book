package com.chen.controller;

import com.chen.entity.Book;
import com.chen.service.BookService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class BookController extends Controller {
    @Inject
    private static BookService bookService;
     //条件查找书
    public void findBookByBookName() {
        //获取页码
        String pageNumber = getPara("pageNumber");
        Book book = getModel(Book.class);
        Page<Book> books = bookService.findBookByBookName(book.getBookName(),pageNumber);
        setAttr("bookByName",books);
        render("borrow_book.html");

    }

    //图书详情
    public void book_detitle(){
        String id = get("id");
        Book bookByBookId = bookService.findBookByBookId(id);
        setAttr("book",bookByBookId);
        render("book_detitle.html");
    }
}
