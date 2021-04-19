package com.management.model.vo;



/**
*
* create by view
*/
public class BricklayerColumnVO {

    private  Integer id;

    private  String originalColumnName;

    private  String simpleColumnType;

    private  String columnType;

    private  String columnKey;

    private  String extra;

    private  String comment;

    private  Integer belongTableId;


/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

    public Integer getId(){
        return id;
        }

    public void setId(Integer id){
    this.id=id;
    }

    public String getOriginalColumnName(){
        return originalColumnName;
        }

    public void setOriginalColumnName(String originalColumnName){
    this.originalColumnName=originalColumnName;
    }

    public String getSimpleColumnType(){
        return simpleColumnType;
        }

    public void setSimpleColumnType(String simpleColumnType){
    this.simpleColumnType=simpleColumnType;
    }

    public String getColumnType(){
        return columnType;
        }

    public void setColumnType(String columnType){
    this.columnType=columnType;
    }

    public String getColumnKey(){
        return columnKey;
        }

    public void setColumnKey(String columnKey){
    this.columnKey=columnKey;
    }

    public String getExtra(){
        return extra;
        }

    public void setExtra(String extra){
    this.extra=extra;
    }

    public String getComment(){
        return comment;
        }

    public void setComment(String comment){
    this.comment=comment;
    }

    public Integer getBelongTableId(){
        return belongTableId;
        }

    public void setBelongTableId(Integer belongTableId){
    this.belongTableId = belongTableId;
    }
}