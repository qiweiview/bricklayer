package com.anicert.serviceI;

import com.anicert.model.dto.BricklayerTableDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
*
* create by view
*/
public interface BricklayerTableServiceI {

    public  BricklayerTableDTO   saveBricklayerTable(BricklayerTableDTO bricklayerTableDTO);

    public  BricklayerTableDTO   updateBricklayerTable(BricklayerTableDTO bricklayerTableDTO);

    public  BricklayerTableDTO   deleteBricklayerTable(BricklayerTableDTO bricklayerTableDTO);

    public  IPage<BricklayerTableDTO>   listBricklayerTablePage(BricklayerTableDTO bricklayerTableDTO);

    public  List<BricklayerTableDTO>   listBricklayerTable(BricklayerTableDTO bricklayerTableDTO);

    public  BricklayerTableDTO   getBricklayerTableById(BricklayerTableDTO bricklayerTableDTO);
}