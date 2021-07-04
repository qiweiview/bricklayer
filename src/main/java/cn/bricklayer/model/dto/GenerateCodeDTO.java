package cn.bricklayer.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GenerateCodeDTO {
    private List<Integer> ids = new ArrayList<>();

    private Integer projectId;


}
