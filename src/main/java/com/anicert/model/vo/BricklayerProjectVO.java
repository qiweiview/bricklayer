package com.anicert.model.vo;


import com.anicert.model.dto.TreeNodeDTO;

public class BricklayerProjectVO {

    private Integer id;

    private String projectName;

    private String projectDescription;


    private String contextPath;

    private TreeNodeDTO tree;

    /*  ------------ data conversion ------------  */


    /*  ------------ getter setter ------------  */


    public TreeNodeDTO getTree() {
        return tree;
    }

    public void setTree(TreeNodeDTO tree) {
        this.tree = tree;
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