package cn.build_support.java_bean;

import cn.anicert.model.dto.BricklayerColumnDTO;
import cn.anicert.utils.StringUtils4V;
import cn.build_support.db_adapter.TypeConverter;
import lombok.Data;

/**
 * 对象列模型
 */
@Data
public class JavaFiledModel {
    private String beanName;//转换后字段名称

    private String originalColumnName;//原字段名称

    private String javaType;//java 类型

    private String columnComment;//备注

    private boolean primaryKey;//主键标识

    private boolean autoIncrease;//自增标识


    public static JavaFiledModel of(BricklayerColumnDTO x) {
        JavaFiledModel javaFiledModel = new JavaFiledModel();
        javaFiledModel.setColumnComment(x.getComment());//备注
        javaFiledModel.setJavaType(TypeConverter.covert(x.getSimpleColumnType()));//java类型
        javaFiledModel.setAutoIncrease(TypeConverter.checkAutoIncrease(x.getExtra()));//自增标识
        javaFiledModel.setOriginalColumnName(x.getOriginalColumnName());//自增
        javaFiledModel.setBeanName(StringUtils4V.underLine2UnCapFirst(x.getOriginalColumnName(), true));//对象属性
        javaFiledModel.setPrimaryKey(TypeConverter.checkPrimary(x.getColumnKey()));//主键
        return javaFiledModel;

    }
}
