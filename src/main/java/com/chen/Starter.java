package com.chen;

import com.chen.config.JfinalConfig;
import com.chen.entity.Book;
import com.chen.entity.User;
import com.jfinal.server.undertow.UndertowServer;

/**
 * 项目启动类
 */
public class Starter {
    public static void main(String[] args) {
        UndertowServer.start(JfinalConfig.class);
    }
}
