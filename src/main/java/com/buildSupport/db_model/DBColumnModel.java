package com.buildSupport.db_model;


import lombok.Data;

/**
 * 对象列模型
 */
@Data
public class DBColumnModel {
    private String belongTableId;

    private String originalColumnName;//原字段名称


    private String simpleColumnType;//字段类型


    private String columnType;//字段类型(含长度)


    private String comment;//备注


    private String columnKey;//主键标识


    private String extra;//自增标识


}
