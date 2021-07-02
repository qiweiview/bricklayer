package cn.anicert.model.d_o;

import cn.anicert.config.LoginInterceptor;
import cn.anicert.model.dto.BricklayerTemplateDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@TableName("bricklayer_template")
@Data
public class BricklayerTemplateDO {

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 模板名称
     */
    @TableField(value = "template_name")
    private String templateName;

    /**
     * 模板内容
     */
    @TableField(value = "template_content")
    private  String templateContent;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 输出后缀
     */
    @TableField(value = "template_suffix")
    private String templateSuffix;

    /**
     * 文件名结束字符
     */
    @TableField(value = "name_end_string")
    private String nameEndString;

    /**
     * 是否字符串模板
     */
    @TableField(value = "string_template")
    private Boolean stringTemplate;



    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_date")
    private Timestamp createDate;

    /**
     * 更新时间
     */
    @TableField(value = "update_date")
    private Timestamp updateDate;

    @TableField(value = "belong_direct_id", exist = false)
    private Integer belongDirectId;


    @TableField(value = "relation_num", exist = false)
    private Integer relationNum;


    /*  ------------ init value ------------  */
    public void doInit() {
        //setDelFlag(false);
        setCreateBy(LoginInterceptor.getCurrentName());
        setUpdateDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        doUpdate();
    }

    public void doUpdate() {
        setUpdateBy(LoginInterceptor.getCurrentName());
        setUpdateDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
    }

    public void doDelete() {
        //setDelFlag(true);
        doUpdate();
        }


/*  ------------ data conversion by model ------------  */
    public  BricklayerTemplateDTO toBricklayerTemplateDTO() {
        BricklayerTemplateDTO bricklayerTemplateDTO = new BricklayerTemplateDTO();
        bricklayerTemplateDTO.setId(getId());
        bricklayerTemplateDTO.setTemplateName(getTemplateName());
        bricklayerTemplateDTO.setTemplateContent(getTemplateContent());
        bricklayerTemplateDTO.setRemark(getRemark());
        bricklayerTemplateDTO.setTemplateSuffix(getTemplateSuffix());
        bricklayerTemplateDTO.setNameEndString(getNameEndString());
        bricklayerTemplateDTO.setStringTemplate(getStringTemplate());
        bricklayerTemplateDTO.setCreateBy(getCreateBy());
        bricklayerTemplateDTO.setUpdateBy(getUpdateBy());
        bricklayerTemplateDTO.setCreateDate(getCreateDate());
        bricklayerTemplateDTO.setUpdateDate(getUpdateDate());
        bricklayerTemplateDTO.setRelationNum(getRelationNum());
        return bricklayerTemplateDTO;

    }


    public static   List<BricklayerTemplateDTO>  toBricklayerTemplateDTOList(List<BricklayerTemplateDO> bricklayerTemplateDOList){
        List<BricklayerTemplateDTO> collect = bricklayerTemplateDOList.stream().map(x -> x.toBricklayerTemplateDTO()).collect(Collectors.toList());
        return collect;
}

}