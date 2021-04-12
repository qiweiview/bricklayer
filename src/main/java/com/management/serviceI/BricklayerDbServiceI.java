package com.management.serviceI;

import com.management.model.dto.BricklayerDbDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
*
* create by view
*/
public interface BricklayerDbServiceI {

    public  BricklayerDbDTO   saveBricklayerDb(BricklayerDbDTO bricklayerDbDTO);

    public  BricklayerDbDTO   updateBricklayerDb(BricklayerDbDTO bricklayerDbDTO);

    public  BricklayerDbDTO   deleteBricklayerDb(BricklayerDbDTO bricklayerDbDTO);

    public  IPage<BricklayerDbDTO>   listBricklayerDbPage(BricklayerDbDTO bricklayerDbDTO);

    public  List<BricklayerDbDTO>   listBricklayerDb(BricklayerDbDTO bricklayerDbDTO);

    public  BricklayerDbDTO   getBricklayerDbById(BricklayerDbDTO bricklayerDbDTO);
}