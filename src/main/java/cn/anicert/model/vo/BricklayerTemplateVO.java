package cn.anicert.model.vo;

import lombok.Data;


@Data
public class BricklayerTemplateVO {
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


    /*  ------------ data conversion ------------  */


}