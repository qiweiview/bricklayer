package cn.bricklayer.serviceI.Impl;

import cn.bricklayer.dao.BricklayerDirectDao;
import cn.bricklayer.model.d_o.BricklayerDirectDO;
import cn.bricklayer.model.dto.BricklayerDirectDTO;
import cn.bricklayer.serviceI.BricklayerDirectServiceI;
import cn.bricklayer.utils.DataNotFoundException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BricklayerDirectServiceImpl implements BricklayerDirectServiceI {

    private final BricklayerDirectDao bricklayerDirectDao;


    @Override
    public BricklayerDirectDTO saveBricklayerDirect(BricklayerDirectDTO bricklayerDirectDTO) {
        BricklayerDirectDO bricklayerDirectDO = bricklayerDirectDTO.toBricklayerDirectDO();
        bricklayerDirectDO.doInit();
        bricklayerDirectDao.insert(bricklayerDirectDO);
        return bricklayerDirectDO.toBricklayerDirectDTO();
        }

    @Override
    public  BricklayerDirectDTO   updateBricklayerDirect(BricklayerDirectDTO bricklayerDirectDTO){
        getBricklayerDirectById(bricklayerDirectDTO);
        BricklayerDirectDO bricklayerDirectDO=bricklayerDirectDTO.toBricklayerDirectDO();
        bricklayerDirectDO.doUpdate();
        bricklayerDirectDao.updateById(bricklayerDirectDO);
        return bricklayerDirectDO.toBricklayerDirectDTO();
        }

    @Override
    public  BricklayerDirectDTO   deleteBricklayerDirect(BricklayerDirectDTO bricklayerDirectDTO){
        bricklayerDirectDTO=getBricklayerDirectById(bricklayerDirectDTO);
        BricklayerDirectDO bricklayerDirectDO=bricklayerDirectDTO.toBricklayerDirectDO();
        bricklayerDirectDO.doDelete();
        bricklayerDirectDao.deleteBricklayerDirect(bricklayerDirectDO);
        return bricklayerDirectDO.toBricklayerDirectDTO();
        }

    @Override
    public  List<BricklayerDirectDTO> listBricklayerDirect(BricklayerDirectDTO bricklayerDirectDTO){
        BricklayerDirectDO bricklayerDirectDO=bricklayerDirectDTO.toBricklayerDirectDO();
        List<BricklayerDirectDO> records = bricklayerDirectDao.listBricklayerDirect( bricklayerDirectDO);
        List<BricklayerDirectDTO> list = BricklayerDirectDO.toBricklayerDirectDTOList(records);
        return list;
        }

    @Override
    public  IPage<BricklayerDirectDTO> listBricklayerDirectPage(BricklayerDirectDTO bricklayerDirectDTO){
        BricklayerDirectDO bricklayerDirectDO=bricklayerDirectDTO.toBricklayerDirectDO();
        Page page = new Page(bricklayerDirectDTO.getCurrent(), bricklayerDirectDTO.getSize());
        IPage<BricklayerDirectDO> pageResult = bricklayerDirectDao.listBricklayerDirectPage(page, bricklayerDirectDO);
        List<BricklayerDirectDO> records = pageResult.getRecords();
        List<BricklayerDirectDTO> list = BricklayerDirectDO.toBricklayerDirectDTOList(records);
        IPage<BricklayerDirectDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
        }

    @Override
    public  BricklayerDirectDTO   getBricklayerDirectById(BricklayerDirectDTO bricklayerDirectDTO){
        BricklayerDirectDO bricklayerDirectDO=bricklayerDirectDTO.toBricklayerDirectDO();
        bricklayerDirectDO=bricklayerDirectDao.getBricklayerDirectById(bricklayerDirectDO);
        if(bricklayerDirectDO==null){
            throw new DataNotFoundException();
        }
        return bricklayerDirectDO.toBricklayerDirectDTO();
        }

}