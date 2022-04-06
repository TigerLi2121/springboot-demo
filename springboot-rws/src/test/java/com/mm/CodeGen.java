package com.mm;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.util.Collections;

public class CodeGen {

    public static void main(String[] args) {
        String url = "jdbc:mysql://106.53.241.47:3416/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";
        String projectPath = System.getProperty("user.dir") + "/springboot-sharding-jdbc";

        FastAutoGenerator.create(url, "root", "root@admin")
                .globalConfig(builder -> builder.author("lwl")
                        .outputDir(projectPath + "/src/main/java")
                        .dateType(DateType.TIME_PACK)
                        .disableOpenDir())
                .packageConfig(builder -> builder.parent("com.mm").moduleName("test")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper")))
                .strategyConfig(builder -> builder.addInclude("order_1").addTableSuffix("_1"))
                .execute();
    }

}
