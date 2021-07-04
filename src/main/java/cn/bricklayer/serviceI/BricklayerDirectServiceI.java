package cn.bricklayer.serviceI;

import cn.bricklayer.model.dto.BricklayerDirectDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;



public interface BricklayerDirectServiceI {

    public  BricklayerDirectDTO   saveBricklayerDirect(BricklayerDirectDTO bricklayerDirectDTO);

    public  BricklayerDirectDTO   updateBricklayerDirect(BricklayerDirectDTO bricklayerDirectDTO);

    public  BricklayerDirectDTO   deleteBricklayerDirect(BricklayerDirectDTO bricklayerDirectDTO);

    public  IPage<BricklayerDirectDTO>   listBricklayerDirectPage(BricklayerDirectDTO bricklayerDirectDTO);

    public  List<BricklayerDirectDTO>   listBricklayerDirect(BricklayerDirectDTO bricklayerDirectDTO);

    public  BricklayerDirectDTO   getBricklayerDirectById(BricklayerDirectDTO bricklayerDirectDTO);
}