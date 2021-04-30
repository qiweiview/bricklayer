package com.anicert.model.dto;

import com.anicert.model.d_o.BricklayerTemplateDO;
import com.anicert.model.vo.BricklayerTemplateVO;
import java.util.List;
import java.util.stream.Collectors;


public class BricklayerTemplateDTO {

    // page field
    private int current;

    private int size;

    /**
     * 基础模板，无法删除
     */
    private  Boolean baseTemplate;
    /**
     * 创建人
     */
    private  String createBy;
    /**
     * 创建日期
     */
    private  java.sql.Timestamp createDate;
    /**
     * 是否字符串模板
     */
    private  Boolean fixedTemplate;
    /**
     * 主键
     */
    private  Integer id;
    /**
     * 文件名结束字符
     */
    private  String nameEndString;
    /**
     * 备注
     */
    private  String remark;
    /**
     * 模板内容
     */
    private  String templateContent;
    /**
     * 模板名称
     */
    private  String templateName;
    /**
     * 输出后缀
     */
    private  String templateSuffix;
    /**
     * 修改人
     */
    private  String updateBy;
    /**
     * 修改日期
     */
    private  java.sql.Timestamp updateDate;


/*  ------------ data conversion ------------  */
public  BricklayerTemplateDO toBricklayerTemplateDO(){
        BricklayerTemplateDO bricklayerTemplateDO =new BricklayerTemplateDO();
        bricklayerTemplateDO.setBaseTemplate(getBaseTemplate());
        bricklayerTemplateDO.setCreateBy(getCreateBy());
        bricklayerTemplateDO.setCreateDate(getCreateDate());
        bricklayerTemplateDO.setFixedTemplate(getFixedTemplate());
        bricklayerTemplateDO.setId(getId());
        bricklayerTemplateDO.setNameEndString(getNameEndString());
        bricklayerTemplateDO.setRemark(getRemark());
        bricklayerTemplateDO.setTemplateContent(getTemplateContent());
        bricklayerTemplateDO.setTemplateName(getTemplateName());
        bricklayerTemplateDO.setTemplateSuffix(getTemplateSuffix());
        bricklayerTemplateDO.setUpdateBy(getUpdateBy());
        bricklayerTemplateDO.setUpdateDate(getUpdateDate());
        return bricklayerTemplateDO;

        }

public  BricklayerTemplateVO toBricklayerTemplateVO(){
        BricklayerTemplateVO bricklayerTemplateVO =new BricklayerTemplateVO();
        bricklayerTemplateVO.setBaseTemplate(getBaseTemplate());
        bricklayerTemplateVO.setCreateBy(getCreateBy());
        bricklayerTemplateVO.setCreateDate(getCreateDate());
        bricklayerTemplateVO.setFixedTemplate(getFixedTemplate());
        bricklayerTemplateVO.setId(getId());
        bricklayerTemplateVO.setNameEndString(getNameEndString());
        bricklayerTemplateVO.setRemark(getRemark());
        bricklayerTemplateVO.setTemplateContent(getTemplateContent());
        bricklayerTemplateVO.setTemplateName(getTemplateName());
        bricklayerTemplateVO.setTemplateSuffix(getTemplateSuffix());
        bricklayerTemplateVO.setUpdateBy(getUpdateBy());
        bricklayerTemplateVO.setUpdateDate(getUpdateDate());
        return bricklayerTemplateVO;

        }



    public static   List<BricklayerTemplateVO>  toBricklayerTemplateVOList(List<BricklayerTemplateDTO> bricklayerTemplateDTOList){
        List<BricklayerTemplateVO> collect= bricklayerTemplateDTOList.stream().map(x->x.toBricklayerTemplateVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerTemplateDO>  toBricklayerTemplateDOList(List<BricklayerTemplateDTO> bricklayerTemplateDtOList){
        List<BricklayerTemplateDO> collect= bricklayerTemplateDtOList.stream().map(x->x.toBricklayerTemplateDO()).collect(Collectors.toList());
        return collect;
    }

/*  ------------ getter setter ------------  */
    public int getCurrent(){
        return current;
    }

    public void setCurrent(int current){
        this.current=current;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size=size;
    }


    public Boolean getBaseTemplate(){
        return baseTemplate;
    }

    public void setBaseTemplate(Boolean baseTemplate){
        this.baseTemplate=baseTemplate;
    }

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

    public Boolean getFixedTemplate(){
        return fixedTemplate;
    }

    public void setFixedTemplate(Boolean fixedTemplate){
        this.fixedTemplate=fixedTemplate;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getNameEndString(){
        return nameEndString;
    }

    public void setNameEndString(String nameEndString){
        this.nameEndString=nameEndString;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark=remark;
    }

    public String getTemplateContent(){
        return templateContent;
    }

    public void setTemplateContent(String templateContent){
        this.templateContent=templateContent;
    }

    public String getTemplateName(){
        return templateName;
    }

    public void setTemplateName(String templateName){
        this.templateName=templateName;
    }

    public String getTemplateSuffix(){
        return templateSuffix;
    }

    public void setTemplateSuffix(String templateSuffix){
        this.templateSuffix=templateSuffix;
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