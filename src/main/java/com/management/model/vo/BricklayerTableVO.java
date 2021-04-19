package com.management.model.vo;



/**
*
* create by view
*/
public class BricklayerTableVO {

    private  Integer id;

    private  String originalTableName;

    private  String remark;

    private  String sourceDataBase;

    private  String sourceDevice;


/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

    public Integer getId(){
        return id;
        }

    public void setId(Integer id){
    this.id=id;
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
    this.sourceDevice = sourceDevice;
    }
}