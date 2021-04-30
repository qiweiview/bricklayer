package com.anicert.model.vo;





public class BricklayerTableVO {
    /**
     * 创建人
     */
    private  String createBy;
    /**
     * 创建日期
     */
    private  java.sql.Timestamp createDate;
    /**
     * 
     */
    private  Integer id;
    /**
     * 模型服务对象
     */
    private  String modelServiceTarget;
    /**
     * 来源表名
     */
    private  String originalTableName;
    /**
     * 备注
     */
    private  String remark;
    /**
     * 来源数据库名
     */
    private  String sourceDataBase;
    /**
     * 来源设备
     */
    private  String sourceDevice;
    /**
     * 修改人
     */
    private  String updateBy;
    /**
     * 修改日期
     */
    private  java.sql.Timestamp updateDate;


/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

    public String getCreateBy(){
        return createBy;
        }

    public void setCreateBy(String createBy){
    this.createBy=createBy;
    }

    public java.sql.Timestamp getCreateDate(){
        return createDate;
        }

    public void setCreateDate(java.sql.Timestamp createDate){
    this.createDate=createDate;
    }

    public Integer getId(){
        return id;
        }

    public void setId(Integer id){
    this.id=id;
    }

    public String getModelServiceTarget(){
        return modelServiceTarget;
        }

    public void setModelServiceTarget(String modelServiceTarget){
    this.modelServiceTarget=modelServiceTarget;
    }

    public String getOriginalTableName(){
        return originalTableName;
        }

    public void setOriginalTableName(String originalTableName){
    this.originalTableName=originalTableName;
    }

    public String getRemark(){
        return remark;
        }

    public void setRemark(String remark){
    this.remark=remark;
    }

    public String getSourceDataBase(){
        return sourceDataBase;
        }

    public void setSourceDataBase(String sourceDataBase){
    this.sourceDataBase=sourceDataBase;
    }

    public String getSourceDevice(){
        return sourceDevice;
        }

    public void setSourceDevice(String sourceDevice){
    this.sourceDevice=sourceDevice;
    }

    public String getUpdateBy(){
        return updateBy;
        }

    public void setUpdateBy(String updateBy){
    this.updateBy=updateBy;
    }

    public java.sql.Timestamp getUpdateDate(){
        return updateDate;
        }

    public void setUpdateDate(java.sql.Timestamp updateDate){
    this.updateDate=updateDate;
    }
}