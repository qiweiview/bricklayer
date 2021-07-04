package cn.bricklayer.model.vo;

import lombok.Data;


@Data
public class BricklayerColumnVO {
    /**
     *
     */
    private Integer id;
    /**
     * 原始列名
     */
    private String originalColumnName;
    /**
     * 简单列类型
     */
    private String simpleColumnType;
    /**
     * 列类型（完整）
     */
    private String columnType;
    /**
     * 是否主键
     */
    private String columnKey;
    /**
     * 是否自增
     */
    private String extra;
    /**
     * 列注释
     */
    private String comment;
    /**
     * 所属模型
     */
    private Integer belongTableId;
    /**
     * 列排序
     */
    private Integer columnOrder;


/*  ------------ data conversion ------------  */



}