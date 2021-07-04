package cn.bricklayer.serviceI;

import cn.bricklayer.model.dto.BricklayerColumnDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
*
* create by view
*/
public interface BricklayerColumnServiceI {


    public  BricklayerColumnDTO   saveBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO);

    public  BricklayerColumnDTO   updateBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO);

    public  BricklayerColumnDTO   deleteBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO);

    public  IPage<BricklayerColumnDTO>   listBricklayerColumnPage(BricklayerColumnDTO bricklayerColumnDTO);

    public  List<BricklayerColumnDTO>   listBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO);

    public  BricklayerColumnDTO   getBricklayerColumnById(BricklayerColumnDTO bricklayerColumnDTO);

    List<BricklayerColumnDTO> getBricklayerColumnsByBelongTableId(BricklayerColumnDTO bricklayerColumnDTO);
}