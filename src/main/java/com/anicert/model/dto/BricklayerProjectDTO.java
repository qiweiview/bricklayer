package com.anicert.model.dto;

import com.anicert.model.d_o.BricklayerProjectDO;
import com.anicert.model.vo.BricklayerProjectVO;
import java.util.List;
import java.util.stream.Collectors;


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
     * 基础项目无法删除
     */
    private  Boolean fixProject;
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
public  BricklayerProjectDO toBricklayerProjectDO(){
        BricklayerProjectDO bricklayerProjectDO =new BricklayerProjectDO();
        bricklayerProjectDO.setContextPath(getContextPath());
        bricklayerProjectDO.setCreateBy(getCreateBy());
        bricklayerProjectDO.setCreateDate(getCreateDate());
        bricklayerProjectDO.setFixProject(getFixProject());
        bricklayerProjectDO.setId(getId());
        bricklayerProjectDO.setProjectDescription(getProjectDescription());
        bricklayerProjectDO.setProjectName(getProjectName());
        bricklayerProjectDO.setUpdateBy(getUpdateBy());
        bricklayerProjectDO.setUpdateDate(getUpdateDate());
        return bricklayerProjectDO;

        }

public  BricklayerProjectVO toBricklayerProjectVO(){
        BricklayerProjectVO bricklayerProjectVO =new BricklayerProjectVO();
        bricklayerProjectVO.setContextPath(getContextPath());
        bricklayerProjectVO.setCreateBy(getCreateBy());
        bricklayerProjectVO.setCreateDate(getCreateDate());
        bricklayerProjectVO.setFixProject(getFixProject());
        bricklayerProjectVO.setId(getId());
        bricklayerProjectVO.setProjectDescription(getProjectDescription());
        bricklayerProjectVO.setProjectName(getProjectName());
        bricklayerProjectVO.setUpdateBy(getUpdateBy());
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

    public TreeNodeDTO getTree() {
        return tree;
    }

    public void setTree(TreeNodeDTO tree) {
        this.tree = tree;
    }

    public int getCurrent(){
        return current;
    }

    public void setCurrent(int current){
        this.current=current;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size=size;
    }


    public String getContextPath(){
        return contextPath;
    }

    public void setContextPath(String contextPath){
        this.contextPath=contextPath;
    }

    public String getCreateBy(){
        return createBy;
    }

    public void setCreateBy(String createBy){
        this.createBy=createBy;
    }

    public java.sql.Timestamp getCreateDate(){
        return createDate;
    }

    public void setCreateDate(java.sql.Timestamp createDate){
        this.createDate=createDate;
    }

    public Boolean getFixProject(){
        return fixProject;
    }

    public void setFixProject(Boolean fixProject){
        this.fixProject=fixProject;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getProjectDescription(){
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription){
        this.projectDescription=projectDescription;
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName){
        this.projectName=projectName;
    }

    public String getUpdateBy(){
        return updateBy;
    }

    public void setUpdateBy(String updateBy){
        this.updateBy=updateBy;
    }

    public java.sql.Timestamp getUpdateDate(){
        return updateDate;
    }

    public void setUpdateDate(java.sql.Timestamp updateDate){
        this.updateDate=updateDate;
    }
}