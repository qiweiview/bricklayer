package cn.bricklayer.model.vo;


import cn.bricklayer.model.dto.TreeNodeDTO;
import lombok.Data;

import java.util.List;

@Data
public class BricklayerProjectVO {
    /**
     * 前端访问前缀
     */
    private String contextPath;
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
    private String updateBy;
    /**
     * 修改日期
     */
    private java.sql.Timestamp updateDate;

    private TreeNodeDTO tree;

    private List<BricklayerProjectGlobalVariableVO> globalVariables;

    /*  ------------ data conversion ------------  */


    /*  ------------ getter setter ------------  */

}