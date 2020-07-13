package com.chen.config;

import com.chen.controller.AdminController;
import com.chen.controller.BookController;
import com.chen.controller.TestController;
import com.chen.controller.UserController;
import com.chen.entity._MappingKit;
import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;


public class JfinalConfig extends JFinalConfig {

    /**
     * 定义一个Prop对象 用于接收读取配置文件后返回的对象
     */
    static private Prop prop;

    /**
     * 加载resource配置文件
     *
     */
    public static Prop loadConfig(){
        prop = PropKit.use("config_pro.txt");
        return prop;
    }

    /**
     * 读取数据源
     *
     */
    public static DruidPlugin createDruidPlugin() {
        loadConfig();
        return new DruidPlugin(prop.get("jdbcUrl"),prop.get("user"),prop.get("password"));
    }

    @Override
    public void configConstant(Constants constants) {
        //运行读取配置文件函数
        loadConfig();
        //设计开发者模式（热加载）
        constants.setDevMode(prop.getBoolean("devMode",false));
        //启用依赖注入
        constants.setInjectDependency(true);
        // 配置对超类中的属性进行注入
        constants.setInjectSuperClass(true);
        //文件上传路径
        constants.setBaseUploadPath("pic/");
        //配置 Date 类型转 json 后的格式
        constants.setJsonDatePattern("yyyy-MM-dd");

    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/user",UserController.class,"/html");
        routes.add("/book", BookController.class,"/html");
        routes.add("/admin", AdminController.class,"/html");
        routes.add("/test", TestController.class,"/html");
    }

    @Override
    public void configEngine(Engine engine) {
        engine.addSharedFunction("/html/_paginate.html");
    }

    @Override
    public void configPlugin(Plugins plugins) {
        //配置数据库连接池
        DruidPlugin dp = createDruidPlugin();
        plugins.add(dp);
        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
       //所有映射在 MappingKit 中自动化搞定
        _MappingKit.mapping(arp);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
