package com.management.model.vo;


/**
 * create by view
 */
public class BricklayerTemplateVO {

    private Integer id;

    private String templateName;

    private String templateContent;

    private String remark;


    /*  ------------ data conversion ------------  */


    /*  ------------ getter setter ------------  */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}