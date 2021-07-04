package cn.bricklayer.serviceI.Impl;

import cn.bricklayer.dao.BricklayerProjectGlobalVariableDao;
import cn.bricklayer.model.d_o.BricklayerProjectGlobalVariableDO;
import cn.bricklayer.model.dto.BricklayerProjectGlobalVariableDTO;
import cn.bricklayer.serviceI.BricklayerProjectGlobalVariableServiceI;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BricklayerProjectGlobalVariableServiceImpl implements BricklayerProjectGlobalVariableServiceI {

    private final BricklayerProjectGlobalVariableDao bricklayerProjectGlobalVariableDao;


    @Override
    public BricklayerProjectGlobalVariableDTO saveBricklayerProjectGlobalVariable(BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO = bricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableDO();
        bricklayerProjectGlobalVariableDO.doInit();
        bricklayerProjectGlobalVariableDao.insert(bricklayerProjectGlobalVariableDO);
        return bricklayerProjectGlobalVariableDO.toBricklayerProjectGlobalVariableDTO();
    }

    @Override
    public BricklayerProjectGlobalVariableDTO updateBricklayerProjectGlobalVariable(BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        getBricklayerProjectGlobalVariableById(bricklayerProjectGlobalVariableDTO);
        BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO = bricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableDO();
        bricklayerProjectGlobalVariableDO.doUpdate();
        bricklayerProjectGlobalVariableDao.updateById(bricklayerProjectGlobalVariableDO);
        return bricklayerProjectGlobalVariableDO.toBricklayerProjectGlobalVariableDTO();
    }

    @Override
    public BricklayerProjectGlobalVariableDTO deleteBricklayerProjectGlobalVariable(BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        bricklayerProjectGlobalVariableDTO = getBricklayerProjectGlobalVariableById(bricklayerProjectGlobalVariableDTO);
        BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO = bricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableDO();
        bricklayerProjectGlobalVariableDO.doDelete();
        bricklayerProjectGlobalVariableDao.deleteBricklayerProjectGlobalVariableLogically(bricklayerProjectGlobalVariableDO);
        return bricklayerProjectGlobalVariableDO.toBricklayerProjectGlobalVariableDTO();
    }

    @Override
    public List<BricklayerProjectGlobalVariableDTO> listBricklayerProjectGlobalVariable(BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO = bricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableDO();
        List<BricklayerProjectGlobalVariableDO> records = bricklayerProjectGlobalVariableDao.listBricklayerProjectGlobalVariable(bricklayerProjectGlobalVariableDO);
        List<BricklayerProjectGlobalVariableDTO> list = BricklayerProjectGlobalVariableDO.toBricklayerProjectGlobalVariableDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerProjectGlobalVariableDTO> listBricklayerProjectGlobalVariablePage(BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO = bricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableDO();
        Page page = new Page(bricklayerProjectGlobalVariableDTO.getCurrent(), bricklayerProjectGlobalVariableDTO.getSize());
        IPage<BricklayerProjectGlobalVariableDO> pageResult = bricklayerProjectGlobalVariableDao.listBricklayerProjectGlobalVariablePage(page, bricklayerProjectGlobalVariableDO);
        List<BricklayerProjectGlobalVariableDO> records = pageResult.getRecords();
        List<BricklayerProjectGlobalVariableDTO> list = BricklayerProjectGlobalVariableDO.toBricklayerProjectGlobalVariableDTOList(records);
        IPage<BricklayerProjectGlobalVariableDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerProjectGlobalVariableDTO getBricklayerProjectGlobalVariableById(BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO = bricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableDO();
        bricklayerProjectGlobalVariableDO = bricklayerProjectGlobalVariableDao.getBricklayerProjectGlobalVariableById(bricklayerProjectGlobalVariableDO);
        if (bricklayerProjectGlobalVariableDO == null) {
            throw new RuntimeException("未找到对应数据");
        }
        return bricklayerProjectGlobalVariableDO.toBricklayerProjectGlobalVariableDTO();
    }

    @Override
    public void deleteBricklayerProjectGlobalVariableBatch(BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        List<Integer> ids = bricklayerProjectGlobalVariableDTO.getIds();
        if (ids != null && ids.size() > 0) {
            bricklayerProjectGlobalVariableDao.deleteBricklayerProjectGlobalVariableBatchLogically(ids);
        }
    }

    @Override
    public List<BricklayerProjectGlobalVariableDTO> listBricklayerProjectGlobalVariableByIds(BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        List<Integer> ids = bricklayerProjectGlobalVariableDTO.getIds();
        if (ids != null && ids.size() > 0) {
            List<BricklayerProjectGlobalVariableDO> list = bricklayerProjectGlobalVariableDao.listBricklayerProjectGlobalVariableByIds(ids);
            return BricklayerProjectGlobalVariableDO.toBricklayerProjectGlobalVariableDTOList(list);
        }
        return new ArrayList<>();
    }
}