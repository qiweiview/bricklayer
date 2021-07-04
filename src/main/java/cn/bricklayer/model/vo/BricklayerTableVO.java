package cn.bricklayer.model.vo;

import lombok.Data;


@Data
public class BricklayerTableVO {
    /**
     * 
     */
    private  Integer id;
    /**
     * 来源表名
     */
    private String originalTableName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 来源设备
     */
    private String sourceDevice;
    /**
     * 来源数据库名
     */
    private String sourceDataBase;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建时间
     */
    private java.sql.Timestamp createDate;
    /**
     * 更新时间
     */
    private java.sql.Timestamp updateDate;


    /*  ------------ data conversion ------------  */


}