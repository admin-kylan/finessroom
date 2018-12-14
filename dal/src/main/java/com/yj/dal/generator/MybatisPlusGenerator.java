package com.yj.dal.generator;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈MybatisPlus自动生成工具类〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
public class MybatisPlusGenerator {
    //文件路径
    private static String packageName="admin";
    //作者
    private static String authorName="MP自动生成";
    //需要生成的表
    private static String[] tables={
            "fr_card_supply_record",
    };
    //table前缀
    private static String prefix="";
    private static File file = new File(packageName);
    private static String path = "E://mybatis生成/";
    // private static String path = file.getAbsolutePath();

    public static void main(String[] args) {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("create_user_name", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_user_name", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("CustomerCode", FieldFill.INSERT));
        tableFillList.add(new TableFill("create_user_id", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_user_id", FieldFill.INSERT_UPDATE));
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(path+"/src/main/java")//输出目录
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setOpen(false)//生成后打开文件夹
                        .setIdType(IdType.UUID)
                        .setAuthor(authorName)
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setMapperName("%sMapper")
                        .setXmlName("%sMapper")
                        .setServiceName("I%sService")
                        .setServiceImplName("%sServiceImpl")
                        .setControllerName("%sController")

        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.SQL_SERVER)// 数据库类型
                        .setTypeConvert(new SqlServerTypeConvert() {
                            // 自定义数据库表字段类型转换【可选】
                            @Override
                            public DbColumnType processTypeConvert(String fieldType) {
                                System.out.println("转换类型：" + fieldType);
                                // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                                //    return DbColumnType.BOOLEAN;
                                // }
                                return super.processTypeConvert(fieldType);
                            }
                        })
                        .setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                        .setUsername("ldjiejing")
                        .setPassword("liandeng@0592")
                        .setUrl("jdbc:sqlserver://rm-bp1owsxurh970700b8o.sqlserver.rds.aliyuncs.com:3433;DatabaseName=jiejing")
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                         .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        .setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setInclude(tables) // 需要生成的表
                        .setRestControllerStyle(true)
                        .setExclude(new String[]{}) // 排除生成的表
                        // 自定义实体父类
                        // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                        // 自定义实体，公共字段
                        //.setSuperEntityColumns(new String[]{"test_id"})
                         .setTableFillList(tableFillList)
                        // 自定义 mapper 父类
                        // .setSuperMapperClass("com.baomidou.demo.TestMapper")
                        // 自定义 service 父类
                        .setSuperServiceClass("com.yj.service.base.BaseService")
                        // 自定义 service 实现类父类
                        .setSuperServiceImplClass("com.yj.service.base.BaseServiceImpl")
                // 自定义 controller 父类
                // .setSuperControllerClass("com.ou."+packageName+".base.AbstractController")
                // 【实体】是否生成字段常量（默认 false）
                // public static final String ID = "test_id";
                // .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                // public User setName(String name) {this.name = name; return this;}
                // .setEntityBuilderModel(true)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                // .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                 .setEntityBooleanColumnRemoveIsPrefix(false)
                 .setRestControllerStyle(true)
                 //.setControllerMappingHyphenStyle(true)
        ).setPackageInfo(
                // 包配置
                new PackageConfig()
                        //.setModuleName("User")
                        .setParent("com.yj")// 自定义包路径
                        .setController("web.controller")// 这里是控制器包名，默认 web
                        .setEntity("dal.model")
                        .setMapper("dal.dao")
                        .setService("service.service")
                        .setServiceImpl("service.service.impl")
                        .setXml("xml.mapper")
        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }
                /*.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return null;
                    }
                    // 自定义输出文件目录
                    //@Override
                    // public String outputFile(TableInfo tableInfo) {
                    //     return path+"/src/java/com/ou/dal/xml/" + tableInfo.getEntityName() + "Mapper.xml";
                    // }
                }))*/
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig()
                        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                        //.setController("/template/controller.java.vm")
                // .setEntity("...")
                // .setMapper("...")
                // .setXml("...")
                // .setService("/template/controller.java.vm")
                // .setServiceImpl("...")
        );

        // 执行生成
        mpg.execute();
        //打开文件夹
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        //System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}