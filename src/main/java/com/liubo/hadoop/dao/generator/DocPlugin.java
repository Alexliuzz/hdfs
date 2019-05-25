package com.liubo.hadoop.dao.generator;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 生成注释
 *
 * @author leibing.song
 * @since 2019-02-19
 */
public class DocPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addDoc(topLevelClass);
        return Boolean.TRUE;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addDoc(topLevelClass);
        return Boolean.TRUE;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addDoc(interfaze);
        return true;
    }

    private void addDoc(Object obj) {
        try {
            MethodUtils.invokeMethod(obj, "addJavaDocLine", "/**");
            MethodUtils.invokeMethod(obj, "addJavaDocLine", "  * @author " + System.getenv("USERNAME"));
            MethodUtils.invokeMethod(obj, "addJavaDocLine", "  * @since " + getNowDate());
            MethodUtils.invokeMethod(obj, "addJavaDocLine", "  */");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getNowDate() {
        return LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
