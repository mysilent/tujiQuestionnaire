package com.wang.tujiquestionnaire.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * ClassName: MybatisplusConfig
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/2/28-10:28
 */
public class MybatisplusConfig {
    //获取项目路径
    private static final String projectPath = System.getProperty("user.dir");
    //输出路径
    private static final String outPath = projectPath + "\\src\\main\\java\\";
    // 设置父包名
    private static final String parentPackageName = "com.wang.tujiquestionnaire";
    // 设置父包模块名
    private static final String moduleName = "system";
    // 设置mapperXml 模板路径
    private static final String mapperPath = projectPath + "\\src\\main\\resources\\mapper\\";
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/questionnaire?serverTimezone=UTC", "root", "root")
                .globalConfig(builder -> {
                    builder.author("wang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackageName) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user_history") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .addTableSuffix("_info")
                            .controllerBuilder().enableRestStyle()
                            .entityBuilder().enableLombok();
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
