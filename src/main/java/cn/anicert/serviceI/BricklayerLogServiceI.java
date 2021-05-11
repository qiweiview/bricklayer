package cn.anicert.serviceI;

import cn.anicert.model.dto.BricklayerLogDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;


public interface BricklayerLogServiceI {

    public BricklayerLogDTO saveBricklayerLog(BricklayerLogDTO bricklayerLogDTO);

    public BricklayerLogDTO updateBricklayerLog(BricklayerLogDTO bricklayerLogDTO);

    public BricklayerLogDTO deleteBricklayerLog(BricklayerLogDTO bricklayerLogDTO);

    public IPage<BricklayerLogDTO> listBricklayerLogPage(BricklayerLogDTO bricklayerLogDTO);

    public List<BricklayerLogDTO> listBricklayerLog(BricklayerLogDTO bricklayerLogDTO);

    public BricklayerLogDTO getBricklayerLogById(BricklayerLogDTO bricklayerLogDTO);

    void deleteBricklayerLogBatch(BricklayerLogDTO bricklayerLogDTO);

    List<BricklayerLogDTO> listBricklayerLogByIds(BricklayerLogDTO bricklayerLogDTO);
}