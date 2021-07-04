package cn.bricklayer.model.vo;

import cn.bricklayer.model.dto.BricklayerLogDTO;
import lombok.Data;

import java.util.List;


@Data
public class BricklayerLogBigScreenVO {
    private List<BricklayerLogDTO> userCount;


    private List<BricklayerLogDTO> uriCount;


}