package com.management.serviceI.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.management.dao.BricklayerTemplateDao;
import com.management.model.d_o.BricklayerTemplateDO;
import com.management.model.dto.BricklayerTemplateDTO;
import com.management.model.dto.SimulatedRenderDTO;
import com.management.serviceI.BricklayerTemplateServiceI;
import com.management.utils.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//import cn.anicert.university.common.exception.EntityNotFoundException;

/**
 * create by view
 */

@Service
@RequiredArgsConstructor
public class BricklayerTemplateServiceImpl implements BricklayerTemplateServiceI {

    private final BricklayerTemplateDao bricklayerTemplateDao;



    @Override
    public BricklayerTemplateDTO saveBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO) {
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        bricklayerTemplateDO.doInit();
        bricklayerTemplateDao.insert(bricklayerTemplateDO);
        return bricklayerTemplateDO.toBricklayerTemplateDTO();
    }

    @Override
    public BricklayerTemplateDTO updateBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO) {
        getBricklayerTemplateById(bricklayerTemplateDTO);
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        bricklayerTemplateDO.doUpdate();
        bricklayerTemplateDao.updateById(bricklayerTemplateDO);
        return bricklayerTemplateDO.toBricklayerTemplateDTO();
    }

    @Override
    public BricklayerTemplateDTO deleteBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO) {
        bricklayerTemplateDTO = getBricklayerTemplateById(bricklayerTemplateDTO);
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        bricklayerTemplateDO.doDelete();
        bricklayerTemplateDao.deleteBricklayerTemplate(bricklayerTemplateDO);
        return bricklayerTemplateDO.toBricklayerTemplateDTO();
    }

    @Override
    public List<BricklayerTemplateDTO> listBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO) {
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        List<BricklayerTemplateDO> records = bricklayerTemplateDao.listBricklayerTemplate(bricklayerTemplateDO);
        List<BricklayerTemplateDTO> list = BricklayerTemplateDO.toBricklayerTemplateDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerTemplateDTO> listBricklayerTemplatePage(BricklayerTemplateDTO bricklayerTemplateDTO) {
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        Page page = new Page(bricklayerTemplateDTO.getCurrent(), bricklayerTemplateDTO.getSize());
        IPage<BricklayerTemplateDO> pageResult = bricklayerTemplateDao.listBricklayerTemplatePage(page, bricklayerTemplateDO);
        List<BricklayerTemplateDO> records = pageResult.getRecords();
        List<BricklayerTemplateDTO> list = BricklayerTemplateDO.toBricklayerTemplateDTOList(records);
        IPage<BricklayerTemplateDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerTemplateDTO getBricklayerTemplateById(BricklayerTemplateDTO bricklayerTemplateDTO) {
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        bricklayerTemplateDO = bricklayerTemplateDao.getBricklayerTemplateById(bricklayerTemplateDO);
        if (bricklayerTemplateDO == null) {
            throw new DataNotFoundException();
        }
        return bricklayerTemplateDO.toBricklayerTemplateDTO();
    }



}