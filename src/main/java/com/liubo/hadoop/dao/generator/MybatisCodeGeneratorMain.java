package com.liubo.hadoop.dao.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

public class MybatisCodeGeneratorMain {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);

        Configuration config = cp.parseConfiguration(new ClassPathResource("generatorConfig.xml").getInputStream());
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        System.out.println("代码生成完毕>>>>>>>>>>>>");
    }
}
