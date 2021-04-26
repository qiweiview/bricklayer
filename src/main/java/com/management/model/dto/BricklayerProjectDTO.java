package com.management.model.dto;

import com.management.model.d_o.BricklayerProjectDO;
import com.management.model.vo.BricklayerProjectVO;

import java.util.List;
import java.util.stream.Collectors;


public class BricklayerProjectDTO {

    // page field
    private int current;

    private int size;


    private Integer id;

    private String projectName;

    private String projectDescription;

    private String contextPath;

    private Boolean fixProject;

    private TreeNodeDTO tree;


    /*  ------------ data conversion ------------  */
    public BricklayerProjectDO toBricklayerProjectDO() {
        BricklayerProjectDO bricklayerProjectDO = new BricklayerProjectDO();
        bricklayerProjectDO.setId(getId());
        bricklayerProjectDO.setFixProject(getFixProject());
        bricklayerProjectDO.setProjectName(getProjectName());
        bricklayerProjectDO.setProjectDescription(getProjectDescription());
        bricklayerProjectDO.setContextPath(getContextPath());
        return bricklayerProjectDO;

    }

    public BricklayerProjectVO toBricklayerProjectVO() {
        BricklayerProjectVO bricklayerProjectVO = new BricklayerProjectVO();
        bricklayerProjectVO.setId(getId());
        bricklayerProjectVO.setProjectName(getProjectName());
        bricklayerProjectVO.setProjectDescription(getProjectDescription());
        bricklayerProjectVO.setContextPath(getContextPath());
        bricklayerProjectVO.setTree(getTree());
        return bricklayerProjectVO;

    }


    public static List<BricklayerProjectVO> toBricklayerProjectVOList(List<BricklayerProjectDTO> bricklayerProjectDTOList) {
        List<BricklayerProjectVO> collect = bricklayerProjectDTOList.stream().map(x -> x.toBricklayerProjectVO()).collect(Collectors.toList());
        return collect;
    }

    public static List<BricklayerProjectDO> toBricklayerProjectDOList(List<BricklayerProjectDTO> bricklayerProjectDTOList) {
        List<BricklayerProjectDO> collect = bricklayerProjectDTOList.stream().map(x -> x.toBricklayerProjectDO()).collect(Collectors.toList());
        return collect;
    }

    /*  ------------ getter setter ------------  */

    public Boolean getFixProject() {
        return fixProject;
    }

    public void setFixProject(Boolean fixProject) {
        this.fixProject = fixProject;
    }

    public TreeNodeDTO getTree() {
        return tree;
    }

    public void setTree(TreeNodeDTO tree) {
        this.tree = tree;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}