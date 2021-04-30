package com.anicert.model.d_o;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.anicert.model.dto.BricklayerDirectTemplateRelationDTO;

import java.util.List;
import java.util.stream.Collectors;



@TableName("bricklayer_direct_template_relation")
public class BricklayerDirectTemplateRelationDO {

    /**
     * 归属文件夹
     */
    @TableField(value = "belong_direct_id")
    private  Integer belongDirectId;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模板id
     */
    @TableField(value = "template_id")
    private Integer templateId;

    @TableField(value = "template_name", exist = false)
    private String templateName;


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
    public  BricklayerDirectTemplateRelationDTO toBricklayerDirectTemplateRelationDTO(){
    BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO =new BricklayerDirectTemplateRelationDTO();
    bricklayerDirectTemplateRelationDTO.setBelongDirectId(getBelongDirectId());
    bricklayerDirectTemplateRelationDTO.setId(getId());
    bricklayerDirectTemplateRelationDTO.setTemplateId(getTemplateId());
        return bricklayerDirectTemplateRelationDTO;

    }


    public static List<BricklayerDirectTemplateRelationDTO> toBricklayerDirectTemplateRelationDTOList(List<BricklayerDirectTemplateRelationDO> bricklayerDirectTemplateRelationDOList) {
        List<BricklayerDirectTemplateRelationDTO> collect = bricklayerDirectTemplateRelationDOList.stream().map(x -> x.toBricklayerDirectTemplateRelationDTO()).collect(Collectors.toList());
        return collect;
    }

    /*  ------------ getter setter ------------  */

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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