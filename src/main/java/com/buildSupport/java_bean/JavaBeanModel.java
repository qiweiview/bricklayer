package com.buildSupport.java_bean;


import com.buildSupport.utils.StringUtils4V;
import com.management.model.dto.BricklayerColumnDTO;
import com.management.model.dto.BricklayerTableDTO;
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

    private String basePath;

    private String contextPath;

    private List<JavaFiledModel> fieldList = new ArrayList();


    public static JavaBeanModel of(BricklayerTableDTO x, String basePath, String contextPath) {
        JavaBeanModel javaBeanModel = new JavaBeanModel();
        String originalTableName = x.getOriginalTableName();
        javaBeanModel.setClassName(StringUtils4V.underLine2UnCapFirst(originalTableName, false));
        javaBeanModel.setOriginalName(x.getOriginalTableName());
        javaBeanModel.setContextPath(contextPath);//设置请求地址前缀
        javaBeanModel.handleBasePath(basePath);

        List<JavaFiledModel> dbColumnModelList = createDbColumnModelList(x.getBricklayerColumnDTOList(), basePath);
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

    private static List<JavaFiledModel> createDbColumnModelList(List<BricklayerColumnDTO> dbColumnModelList, String basePath) {
        return dbColumnModelList.stream().map(x -> JavaFiledModel.of(x)).collect(Collectors.toList());

    }

    public void handleBasePath(String basePath) {
        //分隔符转换
        basePath = StringUtils4V.systemPath2JavaPackagePath(basePath);

        //去除头部
        if (basePath.startsWith(".")) {
            basePath = basePath.substring(1);
        }

        //去除尾部
        if (basePath.endsWith(".")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }

        setBasePath(basePath);

    }
}
