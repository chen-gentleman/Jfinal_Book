package com.chen.utils;

import java.util.UUID;

//图书名字类，防止书本名字重复
public class BookName_util {

    //获取uuid
    private static String get_uuid(){
        return UUID.randomUUID().toString();
    }

    //赋值文件名
    public static String get_name(String fileName){
        int lastIndexOf = fileName.lastIndexOf(".");
        String substring = fileName.substring(lastIndexOf,fileName.length());
        return get_uuid() + substring;
    }
}
