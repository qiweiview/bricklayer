package cn.anicert.model.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNodeDTO {
    private Integer id;

    private String type;

    private String level;

    private String label;

    private Integer templateId;

    private List<TreeNodeDTO> children = new ArrayList<>();


    public void toBeRoot(){
        setLevel("root");
        setChildren(new ArrayList<>());
        setType("direct");
        setLabel("/");
    }

    public void addChild(TreeNodeDTO treeNodeDTO) {
        children.add(treeNodeDTO);
    }

}