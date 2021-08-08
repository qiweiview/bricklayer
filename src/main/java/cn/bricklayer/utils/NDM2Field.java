package cn.bricklayer.utils;

import cn.bricklayer.model.dto.BricklayerColumnDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NDM2Field {
    private String name;

    private String type;

    private Integer length;

    private Integer size;

    private String comment = "";

    public static BricklayerColumnDTO toBricklayerColumnDTO(NDM2Field x) {
        BricklayerColumnDTO bricklayerColumnDTO = new BricklayerColumnDTO();
        bricklayerColumnDTO.setOriginalColumnName(x.getName());
        bricklayerColumnDTO.setColumnType(x.getType());
        bricklayerColumnDTO.setSimpleColumnType(x.getType());
        bricklayerColumnDTO.setComment(x.getComment());
        int size = 0;
        if (x.getLength() != null) {
            size = x.getLength();
        }
        if (x.getSize() != null) {
            size = x.getSize();
        }
        bricklayerColumnDTO.setSize(size);
        return bricklayerColumnDTO;

    }
}
