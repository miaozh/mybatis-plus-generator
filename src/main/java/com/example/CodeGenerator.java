package com.example;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * 执行生成代码类
 *
 * @author Zhihong Miao
 */
public class CodeGenerator {

    /**
     * author
     */
    private static final String AUTHOR = "Zhihong Miao";

    /**
     * 项目路径
     */
    private static final String PROJECT_PATH = System.getProperty("user.dir");

    /**
     * 父包名
     */
    private static final String PARENT = "com.example";

    /**
     * 模块儿名
     */
    private static final String MODEL_NAME = "user";

    /**
     * 表名，多个表用英文逗号分隔
     */
    private static final String TABLES = "t_user,t_student";

    /**
     * 表前缀，例如某公司规范，所有表名必须以t_开头，生成model时候会生成@TableName注解标明表的名称
     */
    private static final String TABLE_PREFIX = "t_";

    /**
     * 数据库连接地址
     */
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8";

    /**
     * 数据驱动名称
     */
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 数据库用户名
     */
    private static final String USERNAME = "root";

    /**
     * 数据库密码
     */
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 指定模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        setGlobalConfig(mpg);

        // 数据源配置
        dataSourceConfig(mpg);

        // 包配置
        packageConfig(mpg);

        // 自定义配置
        injectionConfig(mpg);

        // 策略配置
        strategyConfig(mpg);

        // 执行
        mpg.execute();
    }

    private static void setGlobalConfig(AutoGenerator mpg) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(PROJECT_PATH + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);
    }

    private static void dataSourceConfig(AutoGenerator mpg) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);
    }

    private static void packageConfig(AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(MODEL_NAME);
        pc.setParent(PARENT);
        mpg.setPackageInfo(pc);
    }

    private static void injectionConfig(AutoGenerator mpg) {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // 此处自定义了实体对象和mapper.xml，因为公司规范要求表名以t_开头，字段名以f_开头，所以做了特殊处理

        String versionPath = "templates/v2/";
        templateConfig.setController(versionPath + "controller.java");
        templateConfig.setService(versionPath + "service.java");
        templateConfig.setServiceImpl(versionPath + "serviceImpl.java");
        templateConfig.setMapper(versionPath + "mapper.java");
        templateConfig.setXml(versionPath + "mapper.xml");
        templateConfig.setEntity(versionPath + "entity.java");
        mpg.setTemplate(templateConfig);
    }

    private static void strategyConfig(AutoGenerator mpg) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLES.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(TABLE_PREFIX);
        mpg.setStrategy(strategy);
    }

}
