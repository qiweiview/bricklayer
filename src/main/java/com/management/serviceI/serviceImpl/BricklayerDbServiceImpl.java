package com.management.serviceI.serviceImpl;

import com.buildSupport.db_adapter.MysqlAbstractDataSourceInstance;
import com.buildSupport.db_model.DBColumnModel;
import com.buildSupport.db_model.DBTableModel;
import com.management.dao.BricklayerColumnDao;
import com.management.dao.BricklayerTableDao;
import com.management.model.d_o.BricklayerColumnDO;
import com.management.model.d_o.BricklayerDbDO;
import com.management.dao.BricklayerDbDao;
import com.management.model.d_o.BricklayerTableDO;
import com.management.model.dto.BricklayerDbDTO;
import com.management.model.dto.TableDetailDTO;
import com.management.serviceI.BricklayerTableServiceI;
import com.webSupport.model.DBInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.management.serviceI.BricklayerDbServiceI;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

import com.management.utils.DataNotFoundException;

/**
 * create by view
 */

@Service
@RequiredArgsConstructor
public class BricklayerDbServiceImpl implements BricklayerDbServiceI {

   private final  BricklayerColumnDao bricklayerColumnDao;

    private final BricklayerTableDao bricklayerTableDao;

    private final BricklayerDbDao bricklayerDbDao;


    @Override
    public BricklayerDbDTO saveBricklayerDb(BricklayerDbDTO bricklayerDbDTO) {
        BricklayerDbDO bricklayerDbDO = bricklayerDbDTO.toBricklayerDbDO();
        bricklayerDbDO.doInit();
        bricklayerDbDao.insert(bricklayerDbDO);
        return bricklayerDbDO.toBricklayerDbDTO();
    }

    @Override
    public BricklayerDbDTO updateBricklayerDb(BricklayerDbDTO bricklayerDbDTO) {
        getBricklayerDbById(bricklayerDbDTO);
        BricklayerDbDO bricklayerDbDO = bricklayerDbDTO.toBricklayerDbDO();
        bricklayerDbDO.doUpdate();
        bricklayerDbDao.updateById(bricklayerDbDO);
        return bricklayerDbDO.toBricklayerDbDTO();
    }

    @Override
    public BricklayerDbDTO deleteBricklayerDb(BricklayerDbDTO bricklayerDbDTO) {
        bricklayerDbDTO = getBricklayerDbById(bricklayerDbDTO);
        BricklayerDbDO bricklayerDbDO = bricklayerDbDTO.toBricklayerDbDO();
        bricklayerDbDO.doDelete();
        bricklayerDbDao.deleteBricklayerDb(bricklayerDbDO);
        return bricklayerDbDO.toBricklayerDbDTO();
    }

    @Override
    public List<BricklayerDbDTO> listBricklayerDb(BricklayerDbDTO bricklayerDbDTO) {
        BricklayerDbDO bricklayerDbDO = bricklayerDbDTO.toBricklayerDbDO();
        List<BricklayerDbDO> records = bricklayerDbDao.listBricklayerDb(bricklayerDbDO);
        List<BricklayerDbDTO> list = BricklayerDbDO.toBricklayerDbDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerDbDTO> listBricklayerDbPage(BricklayerDbDTO bricklayerDbDTO) {
        BricklayerDbDO bricklayerDbDO = bricklayerDbDTO.toBricklayerDbDO();
        Page page = new Page(bricklayerDbDTO.getCurrent(), bricklayerDbDTO.getSize());
        IPage<BricklayerDbDO> pageResult = bricklayerDbDao.listBricklayerDbPage(page, bricklayerDbDO);
        List<BricklayerDbDO> records = pageResult.getRecords();
        List<BricklayerDbDTO> list = BricklayerDbDO.toBricklayerDbDTOList(records);
        IPage<BricklayerDbDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerDbDTO getBricklayerDbById(BricklayerDbDTO bricklayerDbDTO) {
        BricklayerDbDO bricklayerDbDO = bricklayerDbDTO.toBricklayerDbDO();
        bricklayerDbDO = bricklayerDbDao.getBricklayerDbById(bricklayerDbDO);
        if (bricklayerDbDO == null) {
            throw new DataNotFoundException();
        }
        return bricklayerDbDO.toBricklayerDbDTO();
    }

    @Override
    public List<BricklayerDbDTO> listBricklayerDbAll() {
        List<BricklayerDbDO> list = bricklayerDbDao.listBricklayerDbAll();
        List<BricklayerDbDTO> bricklayerDbDTOS = BricklayerDbDO.toBricklayerDbDTOList(list);
        return bricklayerDbDTOS;
    }

    @Override
    public List<String> getDataSourceList(BricklayerDbDTO bricklayerDbDTO) {
        BricklayerDbDO bricklayerDbDO = bricklayerDbDTO.toBricklayerDbDO();
        BricklayerDbDO bricklayerDbById = bricklayerDbDao.getBricklayerDbById(bricklayerDbDO);
        if (bricklayerDbById == null) {
            throw new RuntimeException("can not found the datasource");
        }
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbHost(bricklayerDbById.getDbIp());
        dbInfo.setDbPort(bricklayerDbById.getDbPort() + "");
        dbInfo.setDbUser(bricklayerDbById.getDbUser());
        dbInfo.setDbPassWord(bricklayerDbById.getDbPassword());
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = new MysqlAbstractDataSourceInstance(dbInfo.getConnectionPath(), dbInfo.getDbUser(), dbInfo.getDbPassWord());
        List<String> databases = mysqlAbstractDataSourceInstance.getDatabases();
        return databases;
    }


    @Override
    public DBTableModel getTableDetail(TableDetailDTO tableDetailDTO) {

        BricklayerDbDO bricklayerDbDO = new BricklayerDbDO();
        bricklayerDbDO.setId(tableDetailDTO.getDeviceId());
        BricklayerDbDO bricklayerDbById = bricklayerDbDao.getBricklayerDbById(bricklayerDbDO);
        if (bricklayerDbById == null) {
            throw new RuntimeException("can not found the datasource");
        }

        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbHost(bricklayerDbById.getDbIp());
        dbInfo.setDbPort(bricklayerDbById.getDbPort() + "");
        dbInfo.setDbUser(bricklayerDbById.getDbUser());
        dbInfo.setDbPassWord(bricklayerDbById.getDbPassword());
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = new MysqlAbstractDataSourceInstance(dbInfo.getConnectionPath(), dbInfo.getDbUser(), dbInfo.getDbPassWord());
        DBTableModel dbTableModel = mysqlAbstractDataSourceInstance.getDBTableModelByName(tableDetailDTO.getTableName());

        return dbTableModel;
    }

    @Override
    public void saveSingleModel(DBTableModel dbTableModel) {
        String originalTableName = dbTableModel.getOriginalTableName();
        BricklayerTableDO bricklayerTableDO = new BricklayerTableDO();
        bricklayerTableDO.setOriginalTableName(originalTableName);

        int insert = bricklayerTableDao.insert(bricklayerTableDO);


        List<DBColumnModel> dbColumnModelList = dbTableModel.getDbColumnModelList();
        dbColumnModelList.forEach(x->{
            BricklayerColumnDO bricklayerColumnDO = new BricklayerColumnDO();
            bricklayerColumnDO.setTableId(bricklayerTableDO.getId());
            bricklayerColumnDO.setColumnKey(x.getColumnKey());
            bricklayerColumnDO.setColumnType(x.getColumnType());
            bricklayerColumnDO.setComment(x.getComment());
            bricklayerColumnDO.setExtra(x.getExtra());
            bricklayerColumnDO.setOriginalColumnName(x.getOriginalColumnName());
            bricklayerColumnDO.setSimpleColumnType(x.getSimpleColumnType());
            bricklayerColumnDao.insert(bricklayerColumnDO);
        });
    }

}