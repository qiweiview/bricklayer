package com.anicert.serviceI.serviceImpl;

import com.anicert.model.d_o.BricklayerColumnDO;
import com.anicert.dao.BricklayerColumnDao;
import com.anicert.model.dto.BricklayerColumnDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.anicert.serviceI.BricklayerColumnServiceI;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

import com.anicert.utils.DataNotFoundException;

/**
 * create by view
 */

@Service
@RequiredArgsConstructor
public class BricklayerColumnServiceImpl implements BricklayerColumnServiceI {

    private final BricklayerColumnDao bricklayerColumnDao;


    @Override
    public BricklayerColumnDTO saveBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO) {
        BricklayerColumnDO bricklayerColumnDO = bricklayerColumnDTO.toBricklayerColumnDO();
        bricklayerColumnDO.doInit();
        bricklayerColumnDao.insert(bricklayerColumnDO);
        return bricklayerColumnDO.toBricklayerColumnDTO();
    }

    @Override
    public BricklayerColumnDTO updateBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO) {
        getBricklayerColumnById(bricklayerColumnDTO);
        BricklayerColumnDO bricklayerColumnDO = bricklayerColumnDTO.toBricklayerColumnDO();
        bricklayerColumnDO.doUpdate();
        bricklayerColumnDao.updateById(bricklayerColumnDO);
        return bricklayerColumnDO.toBricklayerColumnDTO();
    }

    @Override
    public BricklayerColumnDTO deleteBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO) {
        bricklayerColumnDTO = getBricklayerColumnById(bricklayerColumnDTO);
        BricklayerColumnDO bricklayerColumnDO = bricklayerColumnDTO.toBricklayerColumnDO();
        bricklayerColumnDO.doDelete();
        bricklayerColumnDao.deleteBricklayerColumn(bricklayerColumnDO);
        return bricklayerColumnDO.toBricklayerColumnDTO();
    }

    @Override
    public List<BricklayerColumnDTO> listBricklayerColumn(BricklayerColumnDTO bricklayerColumnDTO) {
        BricklayerColumnDO bricklayerColumnDO = bricklayerColumnDTO.toBricklayerColumnDO();
        List<BricklayerColumnDO> records = bricklayerColumnDao.listBricklayerColumn(bricklayerColumnDO);
        List<BricklayerColumnDTO> list = BricklayerColumnDO.toBricklayerColumnDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerColumnDTO> listBricklayerColumnPage(BricklayerColumnDTO bricklayerColumnDTO) {
        BricklayerColumnDO bricklayerColumnDO = bricklayerColumnDTO.toBricklayerColumnDO();
        Page page = new Page(bricklayerColumnDTO.getCurrent(), bricklayerColumnDTO.getSize());
        IPage<BricklayerColumnDO> pageResult = bricklayerColumnDao.listBricklayerColumnPage(page, bricklayerColumnDO);
        List<BricklayerColumnDO> records = pageResult.getRecords();
        List<BricklayerColumnDTO> list = BricklayerColumnDO.toBricklayerColumnDTOList(records);
        IPage<BricklayerColumnDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerColumnDTO getBricklayerColumnById(BricklayerColumnDTO bricklayerColumnDTO) {
        BricklayerColumnDO bricklayerColumnDO = bricklayerColumnDTO.toBricklayerColumnDO();
        bricklayerColumnDO = bricklayerColumnDao.getBricklayerColumnById(bricklayerColumnDO);
        if (bricklayerColumnDO == null) {
            throw new DataNotFoundException();
        }
        return bricklayerColumnDO.toBricklayerColumnDTO();
    }

    @Override
    public List<BricklayerColumnDTO> getBricklayerColumnsByBelongTableId(BricklayerColumnDTO bricklayerColumnDTO) {
        BricklayerColumnDO bricklayerColumnDO = bricklayerColumnDTO.toBricklayerColumnDO();
        List<BricklayerColumnDO> bricklayerColumnDOS = bricklayerColumnDao.getBricklayerColumnsByBelongTableId(bricklayerColumnDO);
        List<BricklayerColumnDTO> bricklayerColumnDTOS = BricklayerColumnDO.toBricklayerColumnDTOList(bricklayerColumnDOS);
        return bricklayerColumnDTOS;
    }

}