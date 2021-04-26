package com.anicert.serviceI.Impl;

import com.anicert.model.dto.BricklayerUserDTO;
import com.anicert.model.d_o.BricklayerUserDO;
import com.anicert.dao.BricklayerUserDao;
import com.anicert.serviceI.BricklayerUserServiceI;
import com.anicert.utils.DataNotFoundException;
import com.management.utils.MessageRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BricklayerUserServiceImpl implements BricklayerUserServiceI {

    private final BricklayerUserDao bricklayerUserDao;


    @Override
    public BricklayerUserDTO saveBricklayerUser(BricklayerUserDTO bricklayerUserDTO) {
        BricklayerUserDTO bricklayerUserByName = getBricklayerUserByName(bricklayerUserDTO);
        if (bricklayerUserByName!=null){
        throw new MessageRuntimeException("用户 "+bricklayerUserDTO.getUserName()+" 已存在");
        }

        BricklayerUserDO bricklayerUserDO = bricklayerUserDTO.toBricklayerUserDO();
        bricklayerUserDO.doInit();
        bricklayerUserDao.insert(bricklayerUserDO);
        return bricklayerUserDO.toBricklayerUserDTO();
    }

    @Override
    public BricklayerUserDTO getBricklayerUserByName(BricklayerUserDTO bricklayerUserDTO) {
        BricklayerUserDO bricklayerUserDO = bricklayerUserDTO.toBricklayerUserDO();
        bricklayerUserDO = bricklayerUserDao.getBricklayerUserByName(bricklayerUserDO.getUserName());
        return bricklayerUserDO.toBricklayerUserDTO();
    }


    @Override
    public BricklayerUserDTO updateBricklayerUser(BricklayerUserDTO bricklayerUserDTO) {
        getBricklayerUserById(bricklayerUserDTO);
        BricklayerUserDO bricklayerUserDO = bricklayerUserDTO.toBricklayerUserDO();
        bricklayerUserDO.doUpdate();
        bricklayerUserDao.updateById(bricklayerUserDO);
        return bricklayerUserDO.toBricklayerUserDTO();
    }

    @Override
    public BricklayerUserDTO deleteBricklayerUser(BricklayerUserDTO bricklayerUserDTO) {
        bricklayerUserDTO = getBricklayerUserById(bricklayerUserDTO);
        BricklayerUserDO bricklayerUserDO = bricklayerUserDTO.toBricklayerUserDO();
        bricklayerUserDO.doDelete();
        bricklayerUserDao.deleteBricklayerUser(bricklayerUserDO);
        return bricklayerUserDO.toBricklayerUserDTO();
    }

    @Override
    public List<BricklayerUserDTO> listBricklayerUser(BricklayerUserDTO bricklayerUserDTO) {
        BricklayerUserDO bricklayerUserDO = bricklayerUserDTO.toBricklayerUserDO();
        List<BricklayerUserDO> records = bricklayerUserDao.listBricklayerUser(bricklayerUserDO);
        List<BricklayerUserDTO> list = BricklayerUserDO.toBricklayerUserDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerUserDTO> listBricklayerUserPage(BricklayerUserDTO bricklayerUserDTO) {
        BricklayerUserDO bricklayerUserDO = bricklayerUserDTO.toBricklayerUserDO();
        Page page = new Page(bricklayerUserDTO.getCurrent(), bricklayerUserDTO.getSize());
        IPage<BricklayerUserDO> pageResult = bricklayerUserDao.listBricklayerUserPage(page, bricklayerUserDO);
        List<BricklayerUserDO> records = pageResult.getRecords();
        List<BricklayerUserDTO> list = BricklayerUserDO.toBricklayerUserDTOList(records);
        IPage<BricklayerUserDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerUserDTO getBricklayerUserById(BricklayerUserDTO bricklayerUserDTO) {
        BricklayerUserDO bricklayerUserDO = bricklayerUserDTO.toBricklayerUserDO();
        bricklayerUserDO = bricklayerUserDao.getBricklayerUserById(bricklayerUserDO);
        if (bricklayerUserDO == null) {
            throw new MessageRuntimeException("未找到对应数据");
        }
        return bricklayerUserDO.toBricklayerUserDTO();
    }

}