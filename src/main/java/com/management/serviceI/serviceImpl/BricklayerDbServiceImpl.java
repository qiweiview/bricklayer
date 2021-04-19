package com.management.serviceI.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buildSupport.db_adapter.MysqlAbstractDataSourceInstance;
import com.management.dao.BricklayerColumnDao;
import com.management.dao.BricklayerDbDao;
import com.management.dao.BricklayerTableDao;
import com.management.model.d_o.BricklayerColumnDO;
import com.management.model.d_o.BricklayerDbDO;
import com.management.model.d_o.BricklayerTableDO;
import com.management.model.dto.*;
import com.management.serviceI.BricklayerDbServiceI;
import com.management.utils.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * create by view
 */

@Service
@RequiredArgsConstructor
public class BricklayerDbServiceImpl implements BricklayerDbServiceI {

    private final BricklayerColumnDao bricklayerColumnDao;

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

        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = getMysqlAbstractDataSourceInstanceByBricklayerDbDO(bricklayerDbById);
        List<String> databases = mysqlAbstractDataSourceInstance.getDatabases();
        return databases;
    }


    @Override
    public BricklayerTableDTO getTableDetail(TableDetailDTO tableDetailDTO) {

        BricklayerDbDO bricklayerDbDO = new BricklayerDbDO();
        bricklayerDbDO.setId(tableDetailDTO.getDeviceId());
        BricklayerDbDO bricklayerDbById = bricklayerDbDao.getBricklayerDbById(bricklayerDbDO);
        if (bricklayerDbById == null) {
            throw new RuntimeException("can not found the datasource");
        }

        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = getMysqlAbstractDataSourceInstanceByBricklayerDbDO(bricklayerDbById);
        BricklayerTableDTO dbTableModel = mysqlAbstractDataSourceInstance.getDBTableModelByName(tableDetailDTO.getTableName());

        return dbTableModel;
    }

    public MysqlAbstractDataSourceInstance getMysqlAbstractDataSourceInstanceByBricklayerDbDO(BricklayerDbDO bricklayerDbDO) {


        String dbName = "mysql";
        String connectionPath = "jdbc:mysql://" + bricklayerDbDO.getDbIp() + ":" + bricklayerDbDO.getDbPort() + "/" + dbName + "?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = new MysqlAbstractDataSourceInstance(connectionPath, bricklayerDbDO.getDbUser(),bricklayerDbDO.getDbPassword());

        return mysqlAbstractDataSourceInstance;
    }

    @Transactional
    @Override
    public void saveSingleModel(BricklayerTableDTO bricklayerTableDTO) {
        //插入表
        BricklayerTableDO bricklayerTableDO = bricklayerTableDTO.toBricklayerTableDO();
        bricklayerTableDao.insert(bricklayerTableDO);


        //插入列
        List<BricklayerColumnDTO> bricklayerColumnDTOList = bricklayerTableDTO.getBricklayerColumnDTOList();
        bricklayerColumnDTOList.forEach(x -> {
            BricklayerColumnDO bricklayerColumnDO = x.toBricklayerColumnDO();
            bricklayerColumnDO.setBelongTableId(bricklayerTableDO.getId());
            bricklayerColumnDao.insert(bricklayerColumnDO);
        });

    }

    @Override
    public void saveBatchModels(BricklayerTableDTO bricklayerTableDTO) {

        BricklayerDbDO bricklayerDbDO = new BricklayerDbDO();
        bricklayerDbDO.setId(bricklayerTableDTO.getDeviceId());
        BricklayerDbDO bricklayerDbById = bricklayerDbDao.getBricklayerDbById(bricklayerDbDO);
        if (bricklayerDbById == null) {
            throw new RuntimeException("can not found the datasource");
        }

        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = getMysqlAbstractDataSourceInstanceByBricklayerDbDO(bricklayerDbById);
        bricklayerTableDTO.getSelectedTables().forEach(x -> {
            mysqlAbstractDataSourceInstance.addTargetTableName(x);
        });

        List<BricklayerTableDTO> dbTableModels = mysqlAbstractDataSourceInstance.getDBTableModelsByDataSource(bricklayerTableDTO.getSourceDataBase());

        dbTableModels.stream().forEach(x -> {
            BricklayerTableDO bricklayerTableDO = new BricklayerTableDO();
            bricklayerTableDO.setOriginalTableName(x.getOriginalTableName());
            bricklayerTableDO.setSourceDevice(bricklayerTableDTO.getSourceDevice());
            bricklayerTableDO.setSourceDataBase(bricklayerTableDTO.getSourceDataBase());
            bricklayerTableDO.setRemark(bricklayerTableDTO.getRemark());
            bricklayerTableDao.insert(bricklayerTableDO);


            List<BricklayerColumnDTO> dbColumnModelList = x.getBricklayerColumnDTOList();
            dbColumnModelList.forEach(y -> {
                BricklayerColumnDO bricklayerColumnDO = new BricklayerColumnDO();
                bricklayerColumnDO.setColumnKey(y.getColumnKey());
                bricklayerColumnDO.setColumnType(y.getColumnType());
                bricklayerColumnDO.setComment(y.getComment());
                bricklayerColumnDO.setExtra(y.getExtra());
                bricklayerColumnDO.setOriginalColumnName(y.getOriginalColumnName());
                bricklayerColumnDO.setSimpleColumnType(y.getSimpleColumnType());
                bricklayerColumnDO.setBelongTableId(bricklayerTableDO.getId());
                bricklayerColumnDao.insert(bricklayerColumnDO);
            });


        });

    }

    @Override
    public List<String> getTables(TableDetailDTO tableDetailDTO) {
        BricklayerDbDO bricklayerDbDO = new BricklayerDbDO();
        bricklayerDbDO.setId(tableDetailDTO.getDeviceId());
        BricklayerDbDO bricklayerDbById = bricklayerDbDao.getBricklayerDbById(bricklayerDbDO);
        if (bricklayerDbById == null) {
            throw new RuntimeException("can not found the datasource");
        }
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = getMysqlAbstractDataSourceInstanceByBricklayerDbDO(bricklayerDbById);
        List<String> tables = mysqlAbstractDataSourceInstance.getTables(tableDetailDTO.getDataSourceName());
        return tables;
    }

    @Override
    public List<BricklayerTableDTO> getBricklayerTablesByIds(GenerateCodeDTO generateCodeDTO) {

        List<Integer> ids = generateCodeDTO.getIds();
        //default value
        ids.add(-1);

        List<BricklayerColumnDO> bricklayerColumnDOS = bricklayerColumnDao.getBricklayerTablesByIds(generateCodeDTO);
        Map<String, List<BricklayerColumnDO>> collect = bricklayerColumnDOS.stream().collect(Collectors.groupingBy(x -> x.getBelongTableName()));

        List<BricklayerTableDTO> rs = new ArrayList<>();

        collect.forEach((k, v) -> {
            BricklayerTableDTO bricklayerTableDTO = new BricklayerTableDTO();
            bricklayerTableDTO.setOriginalTableName(k);
            bricklayerTableDTO.setBricklayerColumnDTOList(BricklayerColumnDO.toBricklayerColumnDTOList(v));
            rs.add(bricklayerTableDTO);
        });
        return rs;
    }

}