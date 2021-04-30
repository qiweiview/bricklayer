package com.anicert.model.vo;


/**
 * create by view
 */
public class BricklayerTemplateVO {

    private Integer id;

    private String templateName;

    private Boolean fixedTemplate;

    private String nameEndString;

    private String templateSuffix;

    private String templateContent;

    private String remark;


    /*  ------------ data conversion ------------  */


    /*  ------------ getter setter ------------  */

    public String getNameEndString() {
        return nameEndString;
    }

    public void setNameEndString(String nameEndString) {
        this.nameEndString = nameEndString;
    }

    public Boolean getFixedTemplate() {
        return fixedTemplate;
    }

    public void setFixedTemplate(Boolean fixedTemplate) {
        this.fixedTemplate = fixedTemplate;
    }

    public String getTemplateSuffix() {
        return templateSuffix;
    }

    public void setTemplateSuffix(String templateSuffix) {
        this.templateSuffix = templateSuffix;
    }

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