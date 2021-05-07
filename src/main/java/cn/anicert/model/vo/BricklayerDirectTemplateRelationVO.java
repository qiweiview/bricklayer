package cn.anicert.model.vo;





public class BricklayerDirectTemplateRelationVO {
    /**
     * 归属文件夹
     */
    private  Integer belongDirectId;
    /**
     * 主键
     */
    private  Integer id;
    /**
     * 模板id
     */
    private  Integer templateId;
    /**
     * 模板名称
     */
    private  String templateName;


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

    public String getTemplateName(){
        return templateName;
        }

    public void setTemplateName(String templateName){
    this.templateName=templateName;
    }
}