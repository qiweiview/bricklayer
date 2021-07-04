package cn.bricklayer.serviceI;

import cn.bricklayer.model.dto.BricklayerUserDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;



public interface BricklayerUserServiceI {

    public  BricklayerUserDTO   saveBricklayerUser(BricklayerUserDTO bricklayerUserDTO);

    public  BricklayerUserDTO   updateBricklayerUser(BricklayerUserDTO bricklayerUserDTO);

    public  BricklayerUserDTO   deleteBricklayerUser(BricklayerUserDTO bricklayerUserDTO);

    public  IPage<BricklayerUserDTO>   listBricklayerUserPage(BricklayerUserDTO bricklayerUserDTO);

    public  List<BricklayerUserDTO>   listBricklayerUser(BricklayerUserDTO bricklayerUserDTO);

    public  BricklayerUserDTO   getBricklayerUserById(BricklayerUserDTO bricklayerUserDTO);

    BricklayerUserDTO getBricklayerUserByName(BricklayerUserDTO bricklayerUserDTO);


}