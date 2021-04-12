package com.management.serviceI.serviceImpl;

import com.management.model.d_o.BricklayerDbDO;
import com.management.dao.BricklayerDbDao;
import com.management.model.dto.BricklayerDbDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.management.serviceI.BricklayerDbServiceI;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import com.management.utils.DataNotFoundException;
//import cn.anicert.university.common.exception.EntityNotFoundException;

/**
*
* create by view
*/

@Service
public class BricklayerDbServiceImpl implements BricklayerDbServiceI{

    private final BricklayerDbDao bricklayerDbDao;

    @Autowired
    public BricklayerDbServiceImpl(BricklayerDbDao bricklayerDbDao){
        this.bricklayerDbDao =bricklayerDbDao;
        }

    @Override
    public  BricklayerDbDTO   saveBricklayerDb(BricklayerDbDTO bricklayerDbDTO){
        BricklayerDbDO bricklayerDbDO=bricklayerDbDTO.toBricklayerDbDO();
        bricklayerDbDO.doInit();
        bricklayerDbDao.insert(bricklayerDbDO);
        return bricklayerDbDO.toBricklayerDbDTO();
        }

    @Override
    public  BricklayerDbDTO   updateBricklayerDb(BricklayerDbDTO bricklayerDbDTO){
        bricklayerDbDTO=getBricklayerDbById(bricklayerDbDTO);
        BricklayerDbDO bricklayerDbDO=bricklayerDbDTO.toBricklayerDbDO();
        bricklayerDbDO.doUpdate();
        bricklayerDbDao.updateById(bricklayerDbDO);
        return bricklayerDbDO.toBricklayerDbDTO();
        }

    @Override
    public  BricklayerDbDTO   deleteBricklayerDb(BricklayerDbDTO bricklayerDbDTO){
        bricklayerDbDTO=getBricklayerDbById(bricklayerDbDTO);
        BricklayerDbDO bricklayerDbDO=bricklayerDbDTO.toBricklayerDbDO();
        bricklayerDbDO.doDelete();
        bricklayerDbDao.deleteBricklayerDb(bricklayerDbDO);
        return bricklayerDbDO.toBricklayerDbDTO();
        }

    @Override
    public  List<BricklayerDbDTO> listBricklayerDb(BricklayerDbDTO bricklayerDbDTO){
        BricklayerDbDO bricklayerDbDO=bricklayerDbDTO.toBricklayerDbDO();
        List<BricklayerDbDO> records = bricklayerDbDao.listBricklayerDb( bricklayerDbDO);
        List<BricklayerDbDTO> list = BricklayerDbDO.toBricklayerDbDTOList(records);
        return list;
        }

    @Override
    public  IPage<BricklayerDbDTO> listBricklayerDbPage(BricklayerDbDTO bricklayerDbDTO){
        BricklayerDbDO bricklayerDbDO=bricklayerDbDTO.toBricklayerDbDO();
        Page page = new Page(bricklayerDbDTO.getCurrent(), bricklayerDbDTO.getSize());
        IPage<BricklayerDbDO> pageResult = bricklayerDbDao.listBricklayerDbPage(page, bricklayerDbDO);
        List<BricklayerDbDO> records = pageResult.getRecords();
        List<BricklayerDbDTO> list = BricklayerDbDO.toBricklayerDbDTOList(records);
        IPage<BricklayerDbDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
        }

    @Override
    public  BricklayerDbDTO   getBricklayerDbById(BricklayerDbDTO bricklayerDbDTO){
        BricklayerDbDO bricklayerDbDO=bricklayerDbDTO.toBricklayerDbDO();
        bricklayerDbDO=bricklayerDbDao.getBricklayerDbById(bricklayerDbDO);
        if(bricklayerDbDO==null){
            throw new DataNotFoundException();
        }
        return bricklayerDbDO.toBricklayerDbDTO();
        }

}