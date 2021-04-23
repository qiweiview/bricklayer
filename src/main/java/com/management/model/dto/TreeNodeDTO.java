package com.management.model.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNodeDTO {

    private String type;

    private String level;

    private String label;

    private String templateId;

    List<TreeNodeDTO> children=new ArrayList<>();

}