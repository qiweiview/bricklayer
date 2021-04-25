package com.management.model.d_o;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.management.model.dto.BricklayerTemplateDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * create by view
 */
@TableName("bricklayer_template")
public class BricklayerTemplateDO {

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "template_name")
    private String templateName;

    /**
     *
     */
    @TableField(value = "template_suffix")
    private String templateSuffix;

    /**
     *
     */
    @TableField(value = "template_content")
    private String templateContent;

    /**
     *
     */
    @TableField(value = "remark")
    private String remark;


    @TableField(value = "belong_direct_id",exist = false)
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
    public BricklayerTemplateDTO toBricklayerTemplateDTO() {
        BricklayerTemplateDTO bricklayerTemplateDTO = new BricklayerTemplateDTO();
        bricklayerTemplateDTO.setId(getId());
        bricklayerTemplateDTO.setTemplateName(getTemplateName());
        bricklayerTemplateDTO.setTemplateSuffix(getTemplateSuffix());
        bricklayerTemplateDTO.setTemplateContent(getTemplateContent());
        bricklayerTemplateDTO.setRemark(getRemark());
        return bricklayerTemplateDTO;

    }


    public static List<BricklayerTemplateDTO> toBricklayerTemplateDTOList(List<BricklayerTemplateDO> bricklayerTemplateDOList) {
        List<BricklayerTemplateDTO> collect = bricklayerTemplateDOList.stream().map(x -> x.toBricklayerTemplateDTO()).collect(Collectors.toList());
        return collect;
    }

    /*  ------------ getter setter ------------  */

    public String getTemplateSuffix() {
        return templateSuffix;
    }

    public void setTemplateSuffix(String templateSuffix) {
        this.templateSuffix = templateSuffix;
    }

    public Integer getBelongDirectId() {
        return belongDirectId;
    }

    public void setBelongDirectId(Integer belongDirectId) {
        this.belongDirectId = belongDirectId;
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