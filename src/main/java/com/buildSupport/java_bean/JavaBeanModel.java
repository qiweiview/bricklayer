package com.buildSupport.java_bean;


import com.buildSupport.db_model.DBColumnModel;
import com.buildSupport.db_model.DBTableModel;
import com.buildSupport.utils.StringUtils4V;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对象表模型,一个表对应一个模型
 */
@Data
public class JavaBeanModel {

    private String className;//转换后表名

    private String originalName;//原表名

    private String primaryName;

    private ContextModel contextModel;

    private List<JavaFiledModel> fieldList = new ArrayList();


    public static JavaBeanModel of(DBTableModel x, String basePath, String contextPath) {
        JavaBeanModel javaBeanModel = new JavaBeanModel();
        String originalTableName = x.getOriginalTableName();
        javaBeanModel.setClassName(StringUtils4V.underLine2UnCapFirst(originalTableName, false));
        javaBeanModel.setOriginalName(x.getOriginalTableName());

        ContextModel contextModel = createContextModel(javaBeanModel.getClassName(), basePath);
        contextModel.setContextPath(contextPath);//设置请求地址前缀
        javaBeanModel.setContextModel(contextModel);//上下文
        List<JavaFiledModel> dbColumnModelList = createDbColumnModelList(x.getDbColumnModelList(), basePath);
        javaBeanModel.setFieldList(dbColumnModelList);//属性列表
        for (JavaFiledModel javaFiledModel : dbColumnModelList) {
            if (javaFiledModel.isPrimaryKey()) {
                //todo 取第一个主键
                javaBeanModel.setPrimaryName(javaFiledModel.getOriginalColumnName());
                continue;
            }
        }


        return javaBeanModel;
    }

    private static List<JavaFiledModel> createDbColumnModelList(List<DBColumnModel> dbColumnModelList, String basePath) {
        return dbColumnModelList.stream().map(x -> JavaFiledModel.of(x)).collect(Collectors.toList());

    }

    private static ContextModel createContextModel(String className, String basePath) {
        ContextModel contextModel = new ContextModel();
        contextModel.load(className, basePath);
        return contextModel;

    }
}
