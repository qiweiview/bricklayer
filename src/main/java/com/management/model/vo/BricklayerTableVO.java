package com.management.model.vo;



/**
*
* create by view
*/
public class BricklayerTableVO {

    private  Integer id;

    private  String originalTableName;


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
}