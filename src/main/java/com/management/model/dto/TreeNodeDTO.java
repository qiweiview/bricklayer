package com.management.model.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNodeDTO {

    private String type;

    private String level;

    private String label;

    private Integer templateId;

    private List<TreeNodeDTO> children = new ArrayList<>();


    public void addChild(TreeNodeDTO treeNodeDTO) {
        children.add(treeNodeDTO);
    }

}