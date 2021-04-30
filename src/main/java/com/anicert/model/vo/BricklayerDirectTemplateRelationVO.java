package com.anicert.model.vo;





public class BricklayerDirectTemplateRelationVO {

    private  Integer belongDirectId;

    private  Integer id;

    private  Integer templateId;


/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

    public Integer getBelongDirectId(){
        return belongDirectId;
        }

    public void setBelongDirectId(Integer belongDirectId){
    this.belongDirectId=belongDirectId;
    }

    public Integer getId(){
        return id;
        }

    public void setId(Integer id){
    this.id=id;
    }

    public Integer getTemplateId(){
        return templateId;
        }

    public void setTemplateId(Integer templateId){
    this.templateId=templateId;
    }
}