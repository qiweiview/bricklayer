package cn.bricklayer.model.dto;

import lombok.Data;

@Data
public class TableDetailDTO {
    private Integer deviceId;

    private String dataSourceName;

    private String tableName;
}
