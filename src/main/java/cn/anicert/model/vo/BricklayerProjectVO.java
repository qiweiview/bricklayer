package cn.anicert.model.vo;


import cn.anicert.model.dto.TreeNodeDTO;
import lombok.Data;

@Data
public class BricklayerProjectVO {
    /**
     * 前端访问前缀
     */
    private  String contextPath;
    /**
     * 创建人
     */
    private  String createBy;
    /**
     * 创建日期
     */
    private  java.sql.Timestamp createDate;

    /**
     * 主键
     */
    private  Integer id;
    /**
     * 项目描述
     */
    private  String projectDescription;
    /**
     * 项目名
     */
    private  String projectName;
    /**
     * 修改人
     */
    private  String updateBy;
    /**
     * 修改日期
     */
    private  java.sql.Timestamp updateDate;

    private TreeNodeDTO tree;

/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

}