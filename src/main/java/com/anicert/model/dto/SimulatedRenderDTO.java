package com.anicert.model.dto;

import lombok.Data;

@Data
public class SimulatedRenderDTO {
    private Integer modelId;

    private String templateContent;

    private String contextPath;

    private String basePath;

}
