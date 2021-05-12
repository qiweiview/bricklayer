package cn.anicert.serviceI.Impl;

import cn.anicert.dao.BricklayerLogDao;
import cn.anicert.model.d_o.BricklayerLogDO;
import cn.anicert.model.dto.BricklayerLogDTO;
import cn.anicert.serviceI.BricklayerLogServiceI;
import cn.anicert.utils.DataNotFoundException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BricklayerLogServiceImpl implements BricklayerLogServiceI {

    private final BricklayerLogDao bricklayerLogDao;


    @Override
    public BricklayerLogDTO saveBricklayerLog(BricklayerLogDTO bricklayerLogDTO) {
        BricklayerLogDO bricklayerLogDO = bricklayerLogDTO.toBricklayerLogDO();
        bricklayerLogDO.doInit();
        bricklayerLogDao.insert(bricklayerLogDO);
        return bricklayerLogDO.toBricklayerLogDTO();
    }

    @Override
    public BricklayerLogDTO updateBricklayerLog(BricklayerLogDTO bricklayerLogDTO) {
        getBricklayerLogById(bricklayerLogDTO);
        BricklayerLogDO bricklayerLogDO = bricklayerLogDTO.toBricklayerLogDO();
        bricklayerLogDO.doUpdate();
        bricklayerLogDao.updateById(bricklayerLogDO);
        return bricklayerLogDO.toBricklayerLogDTO();
    }

    @Override
    public BricklayerLogDTO deleteBricklayerLog(BricklayerLogDTO bricklayerLogDTO) {
        bricklayerLogDTO = getBricklayerLogById(bricklayerLogDTO);
        BricklayerLogDO bricklayerLogDO = bricklayerLogDTO.toBricklayerLogDO();
        bricklayerLogDO.doDelete();
        bricklayerLogDao.deleteBricklayerLog(bricklayerLogDO);
        return bricklayerLogDO.toBricklayerLogDTO();
    }

    @Override
    public List<BricklayerLogDTO> listBricklayerLog(BricklayerLogDTO bricklayerLogDTO) {
        BricklayerLogDO bricklayerLogDO = bricklayerLogDTO.toBricklayerLogDO();
        List<BricklayerLogDO> records = bricklayerLogDao.listBricklayerLog(bricklayerLogDO);
        List<BricklayerLogDTO> list = BricklayerLogDO.toBricklayerLogDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerLogDTO> listBricklayerLogPage(BricklayerLogDTO bricklayerLogDTO) {
        BricklayerLogDO bricklayerLogDO = bricklayerLogDTO.toBricklayerLogDO();
        Page page = new Page(bricklayerLogDTO.getCurrent(), bricklayerLogDTO.getSize());
        IPage<BricklayerLogDO> pageResult = bricklayerLogDao.listBricklayerLogPage(page, bricklayerLogDO);
        List<BricklayerLogDO> records = pageResult.getRecords();
        List<BricklayerLogDTO> list = BricklayerLogDO.toBricklayerLogDTOList(records);
        IPage<BricklayerLogDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerLogDTO getBricklayerLogById(BricklayerLogDTO bricklayerLogDTO) {
        BricklayerLogDO bricklayerLogDO = bricklayerLogDTO.toBricklayerLogDO();
        bricklayerLogDO = bricklayerLogDao.getBricklayerLogById(bricklayerLogDO);
        if (bricklayerLogDO == null) {
            throw new DataNotFoundException();
        }
        return bricklayerLogDO.toBricklayerLogDTO();
    }

    @Override
    public void deleteBricklayerLogBatch(BricklayerLogDTO bricklayerLogDTO) {
        List<Integer> ids = bricklayerLogDTO.getIds();
        if (ids != null && ids.size() > 0) {
            bricklayerLogDao.deleteBricklayerLogBatch(ids);
        }
    }

    @Override
    public List<BricklayerLogDTO> listBricklayerLogByIds(BricklayerLogDTO bricklayerLogDTO) {
        List<Integer> ids = bricklayerLogDTO.getIds();
        if (ids != null && ids.size() > 0) {
            List<BricklayerLogDO> list = bricklayerLogDao.listBricklayerLogByIds(ids);
            return BricklayerLogDO.toBricklayerLogDTOList(list);
        }
        return new ArrayList<>();
    }

    @Override
    public List<BricklayerLogDTO> getUserCount(BricklayerLogDTO bricklayerLogDTO) {
        List<BricklayerLogDO> list = bricklayerLogDao.getUserCount(bricklayerLogDTO.getStartDate(), bricklayerLogDTO.getEndDate());
        return BricklayerLogDO.toBricklayerLogDTOList(list);
    }

    @Override
    public List<BricklayerLogDTO> getUriCount(BricklayerLogDTO bricklayerLogDTO) {
        List<BricklayerLogDO> list = bricklayerLogDao.getUriCount(bricklayerLogDTO.getStartDate(), bricklayerLogDTO.getEndDate());
        return BricklayerLogDO.toBricklayerLogDTOList(list);
    }
}