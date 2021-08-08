package cn.bricklayer.serviceI.Impl;

import cn.bricklayer.config.LoginInterceptor;
import cn.bricklayer.dao.BricklayerColumnDao;
import cn.bricklayer.dao.BricklayerTableDao;
import cn.bricklayer.model.d_o.BricklayerColumnDO;
import cn.bricklayer.model.d_o.BricklayerTableDO;
import cn.bricklayer.model.dto.BricklayerTableDTO;
import cn.bricklayer.serviceI.BricklayerDbServiceI;
import cn.bricklayer.serviceI.BricklayerTableServiceI;
import cn.bricklayer.utils.DataNotFoundException;
import cn.bricklayer.utils.NDM2Parser;
import cn.bricklayer.utils.NDM2Server;
import cn.bricklayer.utils.NDM2Table;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by view
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BricklayerTableServiceImpl implements BricklayerTableServiceI {

    private final BricklayerDbServiceI bricklayerDbServiceI;
    private final BricklayerTableDao bricklayerTableDao;
    private final BricklayerColumnDao bricklayerColumnDao;


    @Override
    public BricklayerTableDTO saveBricklayerTable(BricklayerTableDTO bricklayerTableDTO) {
        BricklayerTableDO bricklayerTableDO = bricklayerTableDTO.toBricklayerTableDO();
        bricklayerTableDO.doInit();
        bricklayerTableDao.insert(bricklayerTableDO);
        return bricklayerTableDO.toBricklayerTableDTO();
    }

    @Override
    public BricklayerTableDTO updateBricklayerTable(BricklayerTableDTO bricklayerTableDTO) {
        getBricklayerTableById(bricklayerTableDTO);
        BricklayerTableDO bricklayerTableDO = bricklayerTableDTO.toBricklayerTableDO();
        bricklayerTableDO.doUpdate();
        bricklayerTableDao.updateById(bricklayerTableDO);
        return bricklayerTableDO.toBricklayerTableDTO();
    }

    @Transactional
    @Override
    public BricklayerTableDTO deleteBricklayerTable(BricklayerTableDTO bricklayerTableDTO) {
        bricklayerTableDTO = getBricklayerTableById(bricklayerTableDTO);
        BricklayerTableDO bricklayerTableDO = bricklayerTableDTO.toBricklayerTableDO();
        bricklayerTableDO.doDelete();
        bricklayerTableDao.deleteBricklayerTable(bricklayerTableDO);


        BricklayerColumnDO bricklayerColumnDO = new BricklayerColumnDO();
        bricklayerColumnDO.setBelongTableId(bricklayerTableDO.getId());
        bricklayerColumnDao.deleteBricklayerColumnByBelongTableId(bricklayerColumnDO);

        return bricklayerTableDO.toBricklayerTableDTO();
    }

    @Override
    public List<BricklayerTableDTO> listBricklayerTable(BricklayerTableDTO bricklayerTableDTO) {
        BricklayerTableDO bricklayerTableDO = bricklayerTableDTO.toBricklayerTableDO();
        List<BricklayerTableDO> records = bricklayerTableDao.listBricklayerTable(bricklayerTableDO);
        List<BricklayerTableDTO> list = BricklayerTableDO.toBricklayerTableDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerTableDTO> listBricklayerTablePage(BricklayerTableDTO bricklayerTableDTO) {
        BricklayerTableDO bricklayerTableDO = bricklayerTableDTO.toBricklayerTableDO();
        if (bricklayerTableDTO.getOnlyMine() != null && bricklayerTableDTO.getOnlyMine()) {
            bricklayerTableDO.setCreateBy(LoginInterceptor.getCurrentName());
        }
        Page page = new Page(bricklayerTableDTO.getCurrent(), bricklayerTableDTO.getSize());
        IPage<BricklayerTableDO> pageResult = bricklayerTableDao.listBricklayerTablePage(page, bricklayerTableDO);
        List<BricklayerTableDO> records = pageResult.getRecords();
        List<BricklayerTableDTO> list = BricklayerTableDO.toBricklayerTableDTOList(records);
        IPage<BricklayerTableDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerTableDTO getBricklayerTableById(BricklayerTableDTO bricklayerTableDTO) {
        BricklayerTableDO bricklayerTableDO = bricklayerTableDTO.toBricklayerTableDO();
        bricklayerTableDO = bricklayerTableDao.getBricklayerTableById(bricklayerTableDO);
        if (bricklayerTableDO == null) {
            throw new DataNotFoundException();
        }
        return bricklayerTableDO.toBricklayerTableDTO();
    }

    @Override
    public void deleteBricklayerTableBatch(BricklayerTableDTO bricklayerTableDTO) {
        List<Integer> ids = bricklayerTableDTO.getIds();
        if (ids != null && ids.size() > 0) {
            bricklayerTableDao.deleteBricklayerTableBatch(ids);
            bricklayerColumnDao.deleteBricklayerColumnByBelongTableIds(ids);
        }
    }


    @Override
    public void importProject(byte[] bytes, String FileName) {
        String s = new String(bytes);

        NDM2Server ndm2Server = NDM2Parser.ofString(s);

        ndm2Server.getAllTables().stream().map(x -> {
            //todo 类型转换
            BricklayerTableDTO bricklayerTableDTO = NDM2Table.toBricklayerTableDTO(x);
            bricklayerTableDTO.setSourceDataBase(ndm2Server.getName());
            return bricklayerTableDTO;
        }).forEach(x -> {
            //todo 保存
            x.setSourceDevice(FileName + "_import");
            bricklayerDbServiceI.saveSingleModel(x);
        });


    }


}