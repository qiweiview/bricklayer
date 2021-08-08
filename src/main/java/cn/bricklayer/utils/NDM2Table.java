package cn.bricklayer.utils;

import cn.bricklayer.model.dto.BricklayerColumnDTO;
import cn.bricklayer.model.dto.BricklayerTableDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NDM2Table {
    private String name;

    private String comment;

    private String DDL;

    private List<NDM2Field> fields;

    /**
     * 文件对象转存储模型对象
     *
     * @param ndm2Table
     * @return
     */
    public static BricklayerTableDTO toBricklayerTableDTO(NDM2Table ndm2Table) {
        BricklayerTableDTO bricklayerTableDTO = new BricklayerTableDTO();
        bricklayerTableDTO.setOriginalTableName(ndm2Table.getName());
        bricklayerTableDTO.setRemark(ndm2Table.getComment());
        List<BricklayerColumnDTO> collect = ndm2Table.getFields().stream().map(x -> {
            //todo 属性模型转换
            return NDM2Field.toBricklayerColumnDTO(x);
        }).collect(Collectors.toList());
        bricklayerTableDTO.setBricklayerColumnDTOList(collect);
        return bricklayerTableDTO;

    }
}
