package com.management.serviceI.serviceImpl;

import com.management.model.d_o.BricklayerTableDO;
import com.management.dao.BricklayerTableDao;
import com.management.model.dto.BricklayerTableDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.management.serviceI.BricklayerTableServiceI;
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
public class BricklayerTableServiceImpl implements BricklayerTableServiceI{

    private final BricklayerTableDao bricklayerTableDao;

    @Autowired
    public BricklayerTableServiceImpl(BricklayerTableDao bricklayerTableDao){
        this.bricklayerTableDao =bricklayerTableDao;
        }

    @Override
    public  BricklayerTableDTO   saveBricklayerTable(BricklayerTableDTO bricklayerTableDTO){
        BricklayerTableDO bricklayerTableDO=bricklayerTableDTO.toBricklayerTableDO();
        bricklayerTableDO.doInit();
        bricklayerTableDao.insert(bricklayerTableDO);
        return bricklayerTableDO.toBricklayerTableDTO();
        }

    @Override
    public  BricklayerTableDTO   updateBricklayerTable(BricklayerTableDTO bricklayerTableDTO){
        getBricklayerTableById(bricklayerTableDTO);
        BricklayerTableDO bricklayerTableDO=bricklayerTableDTO.toBricklayerTableDO();
        bricklayerTableDO.doUpdate();
        bricklayerTableDao.updateById(bricklayerTableDO);
        return bricklayerTableDO.toBricklayerTableDTO();
        }

    @Override
    public  BricklayerTableDTO   deleteBricklayerTable(BricklayerTableDTO bricklayerTableDTO){
        bricklayerTableDTO=getBricklayerTableById(bricklayerTableDTO);
        BricklayerTableDO bricklayerTableDO=bricklayerTableDTO.toBricklayerTableDO();
        bricklayerTableDO.doDelete();
        bricklayerTableDao.deleteBricklayerTable(bricklayerTableDO);
        return bricklayerTableDO.toBricklayerTableDTO();
        }

    @Override
    public  List<BricklayerTableDTO> listBricklayerTable(BricklayerTableDTO bricklayerTableDTO){
        BricklayerTableDO bricklayerTableDO=bricklayerTableDTO.toBricklayerTableDO();
        List<BricklayerTableDO> records = bricklayerTableDao.listBricklayerTable( bricklayerTableDO);
        List<BricklayerTableDTO> list = BricklayerTableDO.toBricklayerTableDTOList(records);
        return list;
        }

    @Override
    public  IPage<BricklayerTableDTO> listBricklayerTablePage(BricklayerTableDTO bricklayerTableDTO){
        BricklayerTableDO bricklayerTableDO=bricklayerTableDTO.toBricklayerTableDO();
        Page page = new Page(bricklayerTableDTO.getCurrent(), bricklayerTableDTO.getSize());
        IPage<BricklayerTableDO> pageResult = bricklayerTableDao.listBricklayerTablePage(page, bricklayerTableDO);
        List<BricklayerTableDO> records = pageResult.getRecords();
        List<BricklayerTableDTO> list = BricklayerTableDO.toBricklayerTableDTOList(records);
        IPage<BricklayerTableDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
        }

    @Override
    public  BricklayerTableDTO   getBricklayerTableById(BricklayerTableDTO bricklayerTableDTO){
        BricklayerTableDO bricklayerTableDO=bricklayerTableDTO.toBricklayerTableDO();
        bricklayerTableDO=bricklayerTableDao.getBricklayerTableById(bricklayerTableDO);
        if(bricklayerTableDO==null){
            throw new DataNotFoundException();
        }
        return bricklayerTableDO.toBricklayerTableDTO();
        }

}