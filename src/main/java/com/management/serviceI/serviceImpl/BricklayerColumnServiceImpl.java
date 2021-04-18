package com.management.serviceI.serviceImpl;

import com.management.model.d_o.BricklayerColumnDO;
import com.management.dao.BricklayerColumnDao;
import com.management.model.dto.BricklayerColumnDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.management.serviceI.BricklayerColumnServiceI;
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
public class BricklayerColumnServiceImpl implements BricklayerColumnServiceI{

    private final BricklayerColumnDao bricklayerColumnDao;

    @Autowired
    public BricklayerColumnServiceImpl(BricklayerColumnDao bricklayerColumnDao){
        this.bricklayerColumnDao =bricklayerColumnDao;
        }

    @Override
    public  BricklayerColumnDTO   saveBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO){
        BricklayerColumnDO bricklayerColumnDO=bricklayerColumnDTO.toBricklayerColumnDO();
        bricklayerColumnDO.doInit();
        bricklayerColumnDao.insert(bricklayerColumnDO);
        return bricklayerColumnDO.toBricklayerColumnDTO();
        }

    @Override
    public  BricklayerColumnDTO   updateBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO){
        getBricklayerColumnById(bricklayerColumnDTO);
        BricklayerColumnDO bricklayerColumnDO=bricklayerColumnDTO.toBricklayerColumnDO();
        bricklayerColumnDO.doUpdate();
        bricklayerColumnDao.updateById(bricklayerColumnDO);
        return bricklayerColumnDO.toBricklayerColumnDTO();
        }

    @Override
    public  BricklayerColumnDTO   deleteBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO){
        bricklayerColumnDTO=getBricklayerColumnById(bricklayerColumnDTO);
        BricklayerColumnDO bricklayerColumnDO=bricklayerColumnDTO.toBricklayerColumnDO();
        bricklayerColumnDO.doDelete();
        bricklayerColumnDao.deleteBricklayerColumn(bricklayerColumnDO);
        return bricklayerColumnDO.toBricklayerColumnDTO();
        }

    @Override
    public  List<BricklayerColumnDTO> listBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO){
        BricklayerColumnDO bricklayerColumnDO=bricklayerColumnDTO.toBricklayerColumnDO();
        List<BricklayerColumnDO> records = bricklayerColumnDao.listBricklayerColumn( bricklayerColumnDO);
        List<BricklayerColumnDTO> list = BricklayerColumnDO.toBricklayerColumnDTOList(records);
        return list;
        }

    @Override
    public  IPage<BricklayerColumnDTO> listBricklayerColumnPage(BricklayerColumnDTO bricklayerColumnDTO){
        BricklayerColumnDO bricklayerColumnDO=bricklayerColumnDTO.toBricklayerColumnDO();
        Page page = new Page(bricklayerColumnDTO.getCurrent(), bricklayerColumnDTO.getSize());
        IPage<BricklayerColumnDO> pageResult = bricklayerColumnDao.listBricklayerColumnPage(page, bricklayerColumnDO);
        List<BricklayerColumnDO> records = pageResult.getRecords();
        List<BricklayerColumnDTO> list = BricklayerColumnDO.toBricklayerColumnDTOList(records);
        IPage<BricklayerColumnDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
        }

    @Override
    public  BricklayerColumnDTO   getBricklayerColumnById(BricklayerColumnDTO bricklayerColumnDTO){
        BricklayerColumnDO bricklayerColumnDO=bricklayerColumnDTO.toBricklayerColumnDO();
        bricklayerColumnDO=bricklayerColumnDao.getBricklayerColumnById(bricklayerColumnDO);
        if(bricklayerColumnDO==null){
            throw new DataNotFoundException();
        }
        return bricklayerColumnDO.toBricklayerColumnDTO();
        }

}