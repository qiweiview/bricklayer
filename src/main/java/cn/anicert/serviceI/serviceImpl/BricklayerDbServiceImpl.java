package cn.anicert.serviceI.serviceImpl;

import cn.anicert.dao.*;
import cn.anicert.model.d_o.*;
import cn.anicert.model.dto.*;
import cn.anicert.model.vo.GenerationVO;
import cn.anicert.serviceI.BricklayerDbServiceI;
import cn.anicert.utils.*;
import cn.build_support.db_adapter.MysqlAbstractDataSourceInstance;
import cn.build_support.java_bean.JavaBeanModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
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
        bricklayerTableDO.doInit();
        bricklayerTableDao.insert(bricklayerTableDO);


        //插入列
        List<BricklayerColumnDTO> bricklayerColumnDTOList = bricklayerTableDTO.getBricklayerColumnDTOList();
        bricklayerColumnDTOList.forEach(x -> {
            BricklayerColumnDO bricklayerColumnDO = x.toBricklayerColumnDO();
            bricklayerColumnDO.setBelongTableId(bricklayerTableDO.getId());
            bricklayerColumnDO.doInit();
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
            bricklayerTableDO.doInit();
            bricklayerTableDao.insert(bricklayerTableDO);


            List<BricklayerColumnDTO> dbColumnModelList = x.getBricklayerColumnDTOList();
            int[] index = {1};
            dbColumnModelList.forEach(y -> {
                BricklayerColumnDO bricklayerColumnDO = new BricklayerColumnDO();
                bricklayerColumnDO.setColumnKey(y.getColumnKey());
                bricklayerColumnDO.setColumnType(y.getColumnType());
                bricklayerColumnDO.setComment(y.getComment());
                bricklayerColumnDO.setExtra(y.getExtra());
                bricklayerColumnDO.setOriginalColumnName(y.getOriginalColumnName());
                bricklayerColumnDO.setSimpleColumnType(y.getSimpleColumnType());
                bricklayerColumnDO.setBelongTableId(bricklayerTableDO.getId());
                bricklayerColumnDO.setColumnOrder(index[0]++);
                bricklayerColumnDO.doInit();
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
        ids.add(-999);

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
    public GenerationVO generateCode(GenerateCodeDTO generateCodeDTO) {


        ByteArrayOutputStream zipStream = new ByteArrayOutputStream();
        //ZipOutputStream类：完成文件或文件夹的压缩
        ZipOutputStream zipOutputStream = new ZipOutputStream(zipStream);


        List<BricklayerTableDTO> bricklayerTablesByIds = getBricklayerTablesByIds(generateCodeDTO.getIds());


        BricklayerProjectDO bricklayerProjectDO = new BricklayerProjectDO();
        bricklayerProjectDO.setId(generateCodeDTO.getProjectId());
        BricklayerProjectDO bricklayerProjectById = bricklayerProjectDao.getBricklayerProjectById(bricklayerProjectDO);

        if (bricklayerProjectById == null) {
            throw new MessageRuntimeException("not found data");
        }


        //get directs by project id
        List<BricklayerDirectDO> bricklayerDirectDOS = bricklayerDirectDao.listBricklayerDirectsByProjectId(bricklayerProjectById.getId());

        List<Integer> collect = bricklayerDirectDOS.stream().map(x -> x.getId()).collect(Collectors.toList());

        if (collect.size() > 0) {
            //get template by direct
            List<BricklayerTemplateDO> bricklayerTemplateDOS = bricklayerTemplateDao.listBricklayerTemplateByProjectId(collect);
            Map<String, String> map = new HashMap<>();
            bricklayerTemplateDOS.forEach(x -> {
                if (x.getId() > 0) {
                    //todo 大于0模板文件     小于0的是word接口模板
                    map.put(x.getTemplateName(), x.getTemplateContent());
                }
            });

            //freeMaker template
            Configuration configurationByTemplateMap = FreemarkerTemplateBuilder.getConfigurationByTemplateMap(map);

            //目录分组
            Map<Integer, List<BricklayerTemplateDO>> directMap = bricklayerTemplateDOS.stream().collect(Collectors.groupingBy(BricklayerTemplateDO::getBelongDirectId));


            //遍历属性,赋值上下文变量
            Map contextFilesPathMap = new HashMap();
            bricklayerDirectDOS.forEach(x -> {
                List<BricklayerTemplateDO> bricklayerTemplateDOS1 = directMap.get(x.getId());
                if (bricklayerTemplateDOS1 != null) {
                    bricklayerTemplateDOS1.forEach(y -> {
                        if (y.getId() > 0) {
                            //todo 大于0模板文件     小于0的是word接口模板

                            String templateName = y.getTemplateName();
                            //替换.为下划线
                            templateName = templateName.replaceAll("\\.", "_");
                            Map templateMap = new HashMap();
                            contextFilesPathMap.put(templateName, templateMap);

                            String sPath = x.getDirectFullPath();
                            String jPath = StringUtils4V.systemPath2JavaPackagePath(sPath);
                            if (jPath.startsWith(".")) {
                                jPath = jPath.substring(1);
                            }

                            templateMap.put("java_path", jPath);
                            templateMap.put("system_path", sPath);
                        }
                    });
                }


            });


            //遍历目录
            bricklayerDirectDOS.forEach(x -> {
                List<BricklayerTemplateDO> bricklayerTemplateDOS1 = directMap.get(x.getId());
                if (bricklayerTemplateDOS1 != null) {
                    //循环模板
                    bricklayerTemplateDOS1.forEach(y -> {
                        Integer id = y.getId();
                        if (id < 0) {
                            //循环数据模型
                            bricklayerTablesByIds.forEach(z -> {
                                JavaBeanModel of = JavaBeanModel.of(z, x.getDirectFullPath(), bricklayerProjectById.getContextPath());
                                try {
                                    byte[] data = PoiTLCache.getDocumentFile(of);
                                    String name = x.getDirectFullPath() + "/" + of.getClassName() + "_document.docx";
                                    zipOutputStream.putNextEntry(new ZipEntry(name.substring(1)));
                                    zipOutputStream.write(data);
                                    zipOutputStream.closeEntry();
                                } catch (Exception e) {
                                    throw new MessageRuntimeException("文档生成异常：" + e.getMessage());
                                }
                            });
                        } else {
                            String templateName = y.getTemplateName();
                            try {
                                Template template = configurationByTemplateMap.getTemplate(templateName);
                                if (y.getStringTemplate()) {
                                    //todo 字符串模板
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, Charset.forName("utf-8"));
                                    try {
                                        JavaBeanModel javaBeanModel = new JavaBeanModel();
                                        javaBeanModel.setContextFilesPathMap(contextFilesPathMap);
                                        javaBeanModel.handleBasePath(x.getDirectFullPath());
                                        template.process(javaBeanModel, outputStreamWriter);
                                        byte[] bytes = byteArrayOutputStream.toByteArray();
                                        String name = x.getDirectFullPath() + "/" + y.getNameEndString() + y.getTemplateSuffix();
                                        zipOutputStream.putNextEntry(new ZipEntry(name.substring(1)));
                                        zipOutputStream.write(bytes);
                                        zipOutputStream.closeEntry();
                                    } catch (Exception e) {
                                        throw new MessageRuntimeException(templateName + " 模板渲染异常：" + e.getMessage());
                                    }
                                } else {
                                    //todo 模型模板
                                    //循环数据模型
                                    bricklayerTablesByIds.forEach(z -> {

                                        JavaBeanModel of = JavaBeanModel.of(z, x.getDirectFullPath(), bricklayerProjectById.getContextPath());
                                        of.setContextFilesPathMap(contextFilesPathMap);


                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, Charset.forName("utf-8"));
                                        try {
                                            //todo template
                                            String name = x.getDirectFullPath() + "/" + of.getClassName() + NullToEmptyString.handle(y.getNameEndString()) + y.getTemplateSuffix();
                                            template.process(of, outputStreamWriter);
                                            byte[] data = byteArrayOutputStream.toByteArray();

                                            zipOutputStream.putNextEntry(new ZipEntry(name.substring(1)));
                                            zipOutputStream.write(data);
                                            zipOutputStream.closeEntry();
                                        } catch (Exception e) {
                                            throw new MessageRuntimeException(templateName + " 模板渲染异常：" + e.getMessage());
                                        }


                                    });
                                }
                            } catch (IOException e) {
                                throw new MessageRuntimeException(e.getMessage());
                            }
                        }
                    });

                }

            });
        }


        try {
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        GenerationVO generationVO = new GenerationVO();

        generationVO.setData(zipStream.toByteArray());
        generationVO.setFileName(bricklayerProjectById.getProjectName() + "_export.zip");

        return generationVO;
    }


}