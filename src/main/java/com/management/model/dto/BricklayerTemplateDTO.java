package com.management.model.dto;

import com.management.model.d_o.BricklayerTemplateDO;
import com.management.model.vo.BricklayerTemplateVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * create by view
 */
public class BricklayerTemplateDTO {

    // page field
    private int current;

    private int size;


    private Integer id;

    private String templateName;

    private String templateSuffix;

    private String templateContent;

    private String remark;


    /*  ------------ data conversion ------------  */
    public BricklayerTemplateDO toBricklayerTemplateDO() {
        BricklayerTemplateDO bricklayerTemplateDO = new BricklayerTemplateDO();
        bricklayerTemplateDO.setId(getId());
        bricklayerTemplateDO.setTemplateName(getTemplateName());
        bricklayerTemplateDO.setTemplateSuffix(getTemplateSuffix());
        bricklayerTemplateDO.setTemplateContent(getTemplateContent());
        bricklayerTemplateDO.setRemark(getRemark());
        return bricklayerTemplateDO;

    }

    public BricklayerTemplateVO toBricklayerTemplateVO() {
        BricklayerTemplateVO bricklayerTemplateVO = new BricklayerTemplateVO();
        bricklayerTemplateVO.setId(getId());
        bricklayerTemplateVO.setTemplateName(getTemplateName());
        bricklayerTemplateVO.setTemplateSuffix(getTemplateSuffix());
        bricklayerTemplateVO.setTemplateContent(getTemplateContent());
        bricklayerTemplateVO.setRemark(getRemark());
        return bricklayerTemplateVO;

    }


    public static List<BricklayerTemplateVO> toBricklayerTemplateVOList(List<BricklayerTemplateDTO> bricklayerTemplateDTOList) {
        List<BricklayerTemplateVO> collect = bricklayerTemplateDTOList.stream().map(x -> x.toBricklayerTemplateVO()).collect(Collectors.toList());
        return collect;
    }

    public static List<BricklayerTemplateDO> toBricklayerTemplateDOList(List<BricklayerTemplateDTO> bricklayerTemplateDTOList) {
        List<BricklayerTemplateDO> collect = bricklayerTemplateDTOList.stream().map(x -> x.toBricklayerTemplateDO()).collect(Collectors.toList());
        return collect;
    }

    /*  ------------ getter setter ------------  */

    public String getTemplateSuffix() {
        return templateSuffix;
    }

    public void setTemplateSuffix(String templateSuffix) {
        this.templateSuffix = templateSuffix;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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