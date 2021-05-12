package cn.anicert.model.vo;

import cn.anicert.model.dto.BricklayerLogDTO;
import lombok.Data;

import java.util.List;


@Data
public class BricklayerLogBigScreenVO {
    private List<BricklayerLogDTO> userCount;


    private List<BricklayerLogDTO> uriCount;


}