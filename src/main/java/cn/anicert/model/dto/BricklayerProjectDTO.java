package cn.anicert.model.dto;

import cn.anicert.model.d_o.BricklayerProjectDO;
import cn.anicert.model.vo.BricklayerProjectVO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class BricklayerProjectDTO {

    // page field
    private int current;

    private int size;

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
    private String updateBy;
    /**
     * 修改日期
     */
    private java.sql.Timestamp updateDate;


    private TreeNodeDTO tree;

    private Boolean onlyMine;

    /*  ------------ data conversion ------------  */
    public BricklayerProjectDO toBricklayerProjectDO() {
        BricklayerProjectDO bricklayerProjectDO = new BricklayerProjectDO();
        bricklayerProjectDO.setContextPath(getContextPath());
        bricklayerProjectDO.setCreateBy(getCreateBy());
        bricklayerProjectDO.setCreateDate(getCreateDate());
        bricklayerProjectDO.setId(getId());
        bricklayerProjectDO.setProjectDescription(getProjectDescription());
        bricklayerProjectDO.setProjectName(getProjectName());
    bricklayerProjectDO.setUpdateBy(getUpdateBy());
    bricklayerProjectDO.setUpdateDate(getUpdateDate());
    return bricklayerProjectDO;

}

    public BricklayerProjectVO toBricklayerProjectVO() {
        BricklayerProjectVO bricklayerProjectVO = new BricklayerProjectVO();
        bricklayerProjectVO.setContextPath(getContextPath());
        bricklayerProjectVO.setCreateBy(getCreateBy());
        bricklayerProjectVO.setCreateDate(getCreateDate());

        bricklayerProjectVO.setId(getId());
        bricklayerProjectVO.setProjectDescription(getProjectDescription());
        bricklayerProjectVO.setProjectName(getProjectName());
        bricklayerProjectVO.setUpdateBy(getUpdateBy());
        bricklayerProjectVO.setTree(getTree());
        bricklayerProjectVO.setUpdateDate(getUpdateDate());
        return bricklayerProjectVO;

    }


    public static   List<BricklayerProjectVO>  toBricklayerProjectVOList(List<BricklayerProjectDTO> bricklayerProjectDTOList){
        List<BricklayerProjectVO> collect= bricklayerProjectDTOList.stream().map(x->x.toBricklayerProjectVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerProjectDO>  toBricklayerProjectDOList(List<BricklayerProjectDTO> bricklayerProjectDtOList){
        List<BricklayerProjectDO> collect= bricklayerProjectDtOList.stream().map(x->x.toBricklayerProjectDO()).collect(Collectors.toList());
        return collect;
    }

    /*  ------------ getter setter ------------  */

}