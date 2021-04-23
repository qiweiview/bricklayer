package com.management.model.vo;





public class BricklayerDirectVO {

    private  Integer belongProjectId;

    private  String directName;

    private  String directType;

    private  Integer id;

    private  Integer parentDirectId;


/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

    public Integer getBelongProjectId(){
        return belongProjectId;
        }

    public void setBelongProjectId(Integer belongProjectId){
    this.belongProjectId=belongProjectId;
    }

    public String getDirectName(){
        return directName;
        }

    public void setDirectName(String directName){
    this.directName=directName;
    }

    public String getDirectType(){
        return directType;
        }

    public void setDirectType(String directType){
    this.directType=directType;
    }

    public Integer getId(){
        return id;
        }

    public void setId(Integer id){
    this.id=id;
    }

    public Integer getParentDirectId(){
        return parentDirectId;
        }

    public void setParentDirectId(Integer parentDirectId){
    this.parentDirectId=parentDirectId;
    }
}