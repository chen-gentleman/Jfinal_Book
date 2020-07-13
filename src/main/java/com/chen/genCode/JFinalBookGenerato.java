package com.chen.genCode;

import com.chen.config.JfinalConfig;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

/**
 * 数据库表对应的实体类自动生成类
 */
public class JFinalBookGenerato {


    public static void main(String[] args) {
        // base model 所使用的包名
        String baseModelPkg = "com.chen.entity.base";
        // base model 文件保存路径
        String baseModelDir = PathKit.getWebRootPath() + "/src/main/java/com/chen/entity/base";

        // model 所使用的包名
        String modelPkg = "com.chen.entity";
        // model 文件保存路径
        String modelDir = baseModelDir + "/..";

        Generator gernerator = new Generator(getDataSource(), baseModelPkg, baseModelDir, modelPkg, modelDir);
        // 在 getter、setter 方法上生成字段备注内容
        gernerator.setGenerateRemarks(true);
        gernerator.generate();
    }

    //获取数据源
    public static DataSource getDataSource(){
        DruidPlugin druidPlugin = JfinalConfig.createDruidPlugin();
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }
}
