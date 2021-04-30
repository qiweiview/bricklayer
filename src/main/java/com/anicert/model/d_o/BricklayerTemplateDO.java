package com.anicert.model.d_o;

import com.anicert.model.dto.BricklayerTemplateDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;



@TableName("bricklayer_template")
public class BricklayerTemplateDO {

    /**
     * 基础模板，无法删除
     */
    @TableField(value = "base_template")
    private  Boolean baseTemplate;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private  String createBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private  Timestamp createDate;

    /**
     * 是否字符串模板
     */
    @TableField(value = "fixed_template")
    private  Boolean fixedTemplate;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 文件名结束字符
     */
    @TableField(value = "name_end_string")
    private  String nameEndString;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private  String remark;

    /**
     * 模板内容
     */
    @TableField(value = "template_content")
    private  String templateContent;

    /**
     * 模板名称
     */
    @TableField(value = "template_name")
    private  String templateName;

    /**
     * 输出后缀
     */
    @TableField(value = "template_suffix")
    private  String templateSuffix;

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private  String updateBy;

    /**
     * 修改日期
     */
    @TableField(value = "update_date")
    private  Timestamp updateDate;

    @TableField(value = "belong_direct_id", exist = false)
    private Integer belongDirectId;

/*  ------------ init value ------------  */



    public void doInit() {
        //setDelFlag(false);
        //setCreateTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        }

    public void doUpdate() {
        //setUpdateTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        }

    public void doDelete() {
        //setDelFlag(true);
        //doUpdate();
        }


/*  ------------ data conversion by model ------------  */
    public  BricklayerTemplateDTO toBricklayerTemplateDTO(){
    BricklayerTemplateDTO bricklayerTemplateDTO =new BricklayerTemplateDTO();
    bricklayerTemplateDTO.setBaseTemplate(getBaseTemplate());
    bricklayerTemplateDTO.setCreateBy(getCreateBy());
    bricklayerTemplateDTO.setCreateDate(getCreateDate());
    bricklayerTemplateDTO.setFixedTemplate(getFixedTemplate());
    bricklayerTemplateDTO.setId(getId());
    bricklayerTemplateDTO.setNameEndString(getNameEndString());
    bricklayerTemplateDTO.setRemark(getRemark());
    bricklayerTemplateDTO.setTemplateContent(getTemplateContent());
    bricklayerTemplateDTO.setTemplateName(getTemplateName());
    bricklayerTemplateDTO.setTemplateSuffix(getTemplateSuffix());
    bricklayerTemplateDTO.setUpdateBy(getUpdateBy());
    bricklayerTemplateDTO.setUpdateDate(getUpdateDate());
        return bricklayerTemplateDTO;

}


    public static   List<BricklayerTemplateDTO>  toBricklayerTemplateDTOList(List<BricklayerTemplateDO> bricklayerTemplateDOList){
        List<BricklayerTemplateDTO> collect = bricklayerTemplateDOList.stream().map(x -> x.toBricklayerTemplateDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */
public Integer getBelongDirectId() {
    return belongDirectId;
}

    public void setBelongDirectId(Integer belongDirectId) {
        this.belongDirectId = belongDirectId;
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

    public Timestamp getCreateDate(){
    return createDate;
    }

    public void setCreateDate(Timestamp createDate){
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

    public Timestamp getUpdateDate(){
    return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate){
    this.updateDate=updateDate;
    }
}