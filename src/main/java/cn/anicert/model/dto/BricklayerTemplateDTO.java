package cn.anicert.model.dto;

import cn.anicert.model.d_o.BricklayerTemplateDO;
import cn.anicert.model.vo.BricklayerTemplateVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BricklayerTemplateDTO {

    // page field
    private int current;

    private int size;

    private List<Integer> ids;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 备注
     */
    private String remark;

    /**
     * 输出后缀
     */
    private String templateSuffix;

    /**
     * 文件名结束字符
     */
    private String nameEndString;

    /**
     * 是否字符串模板
     */
    private Boolean stringTemplate;


    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private java.sql.Timestamp createDate;

    /**
     * 更新时间
     */
    private java.sql.Timestamp updateDate;


    private Boolean onlyMine;

    private Integer relationNum;


    /*  ------------ data conversion ------------  */
    public BricklayerTemplateDO toBricklayerTemplateDO() {
        BricklayerTemplateDO bricklayerTemplateDO = new BricklayerTemplateDO();
        bricklayerTemplateDO.setId(getId());
        bricklayerTemplateDO.setTemplateName(getTemplateName());
        bricklayerTemplateDO.setTemplateContent(getTemplateContent());
        bricklayerTemplateDO.setRemark(getRemark());
        bricklayerTemplateDO.setTemplateSuffix(getTemplateSuffix());
        bricklayerTemplateDO.setNameEndString(getNameEndString());
        bricklayerTemplateDO.setStringTemplate(getStringTemplate());
        bricklayerTemplateDO.setCreateBy(getCreateBy());
        bricklayerTemplateDO.setUpdateBy(getUpdateBy());
        bricklayerTemplateDO.setCreateDate(getCreateDate());
        bricklayerTemplateDO.setUpdateDate(getUpdateDate());
        bricklayerTemplateDO.setRelationNum(getRelationNum());
        return bricklayerTemplateDO;

    }

public  BricklayerTemplateVO toBricklayerTemplateVO() {
    BricklayerTemplateVO bricklayerTemplateVO = new BricklayerTemplateVO();
    bricklayerTemplateVO.setId(getId());
    bricklayerTemplateVO.setTemplateName(getTemplateName());
    bricklayerTemplateVO.setTemplateContent(getTemplateContent());
    bricklayerTemplateVO.setRemark(getRemark());
    bricklayerTemplateVO.setTemplateSuffix(getTemplateSuffix());
    bricklayerTemplateVO.setNameEndString(getNameEndString());
    bricklayerTemplateVO.setStringTemplate(getStringTemplate());
    bricklayerTemplateVO.setCreateBy(getCreateBy());
    bricklayerTemplateVO.setUpdateBy(getUpdateBy());
    bricklayerTemplateVO.setCreateDate(getCreateDate());
    bricklayerTemplateVO.setUpdateDate(getUpdateDate());
    bricklayerTemplateVO.setRelationNum(getRelationNum());
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

}