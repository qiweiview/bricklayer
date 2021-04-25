package com.management.serviceI.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buildSupport.build_task.output_task.OutPutTask;
import com.buildSupport.db_adapter.MysqlAbstractDataSourceInstance;
import com.buildSupport.java_bean.JavaBeanModel;
import com.buildSupport.utils.FreemarkerTemplateBuilder;
import com.management.dao.*;
import com.management.model.d_o.*;
import com.management.model.dto.*;
import com.management.serviceI.BricklayerDbServiceI;
import com.management.utils.DataNotFoundException;
import com.management.utils.MessageRuntimeException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * create by view
 */

@Service
@RequiredArgsConstructor
public class BricklayerDbServiceImpl implements BricklayerDbServiceI {

    private final BricklayerColumnDao bricklayerColumnDao;

    private final BricklayerTableDao bricklayerTableDao;

    private final BricklayerDbDao bricklayerDbDao;

    private final BricklayerProjectDao bricklayerProjectDao;

    private final BricklayerDirectDao bricklayerDirectDao;

    private final BricklayerTemplateDao bricklayerTemplateDao;


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
            throw new MessageRuntimeException("can not found the datasource");
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
            throw new MessageRuntimeException("can not found the datasource");
        }

        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = getMysqlAbstractDataSourceInstanceByBricklayerDbDO(bricklayerDbById);
        BricklayerTableDTO dbTableModel = mysqlAbstractDataSourceInstance.getDBTableModelByName(tableDetailDTO.getTableName());

        return dbTableModel;
    }

    public MysqlAbstractDataSourceInstance getMysqlAbstractDataSourceInstanceByBricklayerDbDO(BricklayerDbDO bricklayerDbDO) {


        String dbName = "mysql";
        String connectionPath = "jdbc:mysql://" + bricklayerDbDO.getDbIp() + ":" + bricklayerDbDO.getDbPort() + "/" + dbName + "?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = new MysqlAbstractDataSourceInstance(connectionPath, bricklayerDbDO.getDbUser(), bricklayerDbDO.getDbPassword());

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
            throw new MessageRuntimeException("can not found the datasource");
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
            throw new MessageRuntimeException("can not found the datasource");
        }
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = getMysqlAbstractDataSourceInstanceByBricklayerDbDO(bricklayerDbById);
        List<String> tables = mysqlAbstractDataSourceInstance.getTables(tableDetailDTO.getDataSourceName());
        return tables;
    }

    @Override
    public List<BricklayerTableDTO> getBricklayerTablesByIds(List<Integer> ids) {


        //default value
        ids.add(-1);

        List<BricklayerColumnDO> bricklayerColumnDOS = bricklayerColumnDao.getBricklayerTablesByIds(ids);
        if (bricklayerColumnDOS.size() < 1) {
            throw new MessageRuntimeException("not found data");
        }
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

    @Override
    public BricklayerTableDTO getBricklayerTableById(Integer id) {
        List<BricklayerColumnDO> bricklayerColumnDOS = bricklayerColumnDao.getBricklayerTableById(id);
        if (bricklayerColumnDOS.size() < 1) {
            throw new MessageRuntimeException("not found data");
        }

        BricklayerTableDTO bricklayerTableDTO = new BricklayerTableDTO();
        bricklayerTableDTO.setOriginalTableName(bricklayerColumnDOS.get(0).getBelongTableName());
        bricklayerTableDTO.setBricklayerColumnDTOList(BricklayerColumnDO.toBricklayerColumnDTOList(bricklayerColumnDOS));
        return bricklayerTableDTO;
    }

    @Override
    public byte[] generateCode(GenerateCodeDTO generateCodeDTO) {
        ByteArrayOutputStream zipStream = new ByteArrayOutputStream();
        //ZipOutputStream类：完成文件或文件夹的压缩
        ZipOutputStream out = new ZipOutputStream(zipStream);


        List<BricklayerTableDTO> bricklayerTablesByIds = getBricklayerTablesByIds(generateCodeDTO.getIds());


        BricklayerProjectDO bricklayerProjectDO = new BricklayerProjectDO();
        bricklayerProjectDO.setId(generateCodeDTO.getProjectId());
        BricklayerProjectDO bricklayerProjectById = bricklayerProjectDao.getBricklayerProjectById(bricklayerProjectDO);

        if (bricklayerProjectById == null) {
            throw new MessageRuntimeException("not found data");
        }

        List<BricklayerDirectDO> bricklayerDirectDOS = bricklayerDirectDao.listBricklayerDirectsByProjectId(bricklayerProjectById.getId());

        List<Integer> collect = bricklayerDirectDOS.stream().map(x -> x.getId()).collect(Collectors.toList());
        if (collect.size() > 0) {
            List<BricklayerTemplateDO> bricklayerTemplateDOS = bricklayerTemplateDao.listBricklayerTemplateByProjectId(collect);
            Map<String, String> map = new HashMap<>();
            bricklayerTemplateDOS.forEach(x -> {
                map.put(x.getTemplateName(), x.getTemplateContent());
            });

            //freeMaker template
            Configuration configurationByTemplateMap = FreemarkerTemplateBuilder.getConfigurationByTemplateMap(map);

            //目录分组
            Map<Integer, List<BricklayerTemplateDO>> directMap = bricklayerTemplateDOS.stream().collect(Collectors.groupingBy(BricklayerTemplateDO::getBelongDirectId));

            //遍历目录
            bricklayerDirectDOS.forEach(x -> {
                List<BricklayerTemplateDO> bricklayerTemplateDOS1 = directMap.get(x.getId());
                if (bricklayerTemplateDOS1 != null) {
                    //循环模板
                    bricklayerTemplateDOS1.forEach(y -> {
                        String templateName = y.getTemplateName();
                        try {
                            Template template = configurationByTemplateMap.getTemplate(templateName);
                            //循环数据模型
                            bricklayerTablesByIds.forEach(z -> {
                                JavaBeanModel of = JavaBeanModel.of(z, x.getDirectFullPath(), bricklayerProjectById.getContextPath());
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, Charset.forName("utf-8"));
                                try {
                                    template.process(of, outputStreamWriter);
                                    byte[] bytes = byteArrayOutputStream.toByteArray();
                                    String name = x.getDirectFullPath() + File.separator + of.getClassName() +y.getRemark()+ y.getTemplateSuffix();
                                    out.putNextEntry(new ZipEntry(name.substring(1)));
                                    out.write(bytes);
                                    out.closeEntry();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }

            });
        }


        try {
            zipStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipStream.toByteArray();
    }


}