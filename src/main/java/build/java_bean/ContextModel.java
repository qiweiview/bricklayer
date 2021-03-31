package build.java_bean;

import build.utils.SuffixManager;
import build.utils.StringUtils4V;
import lombok.Data;

/**
 * 一个表上下文对应一个表模型
 */
@Data
public class ContextModel {

    //名称
    private String doName;

    private String dtoName;

    private String voName;


    private String controllerName;

    private String serviceIName;

    private String serviceImplName;

    private String daoName;

    private String mapperName;

    private String utilsName;

    //路径（带package）
    private String doPackage;

    private String voPackage;

    private String dtoPackage;

    private String controllerPackage;

    private String serviceIPackage;

    private String serviceImplPackage;

    private String daoPackage;

    private String mapperPackage;

    private String utilsPackage;

    //路径
    private String doBase;

    private String voBase;

    private String dtoBase;

    private String controllerBase;

    private String serviceIBase;

    private String serviceImplBase;

    private String daoBase;

    private String mapperBase;

    private String utilsBase;


    //全路径名称
    private String doPath;

    private String voPath;

    private String dtoPath;

    private String controllerPath;

    private String serviceIPath;

    private String serviceImplPath;

    private String daoPath;

    private String mapperPath;

    private String utilsPath;


    public void load(String className, String basePath) {

        //name
        setDoName(className+ SuffixManager.dOSuffix);
        setDtoName(className+ SuffixManager.dTOSuffix);
        setVoName(className+ SuffixManager.vOSuffix);

        setControllerName(className+ SuffixManager.controllerSuffix);
        setServiceIName(className+ SuffixManager.serviceISuffix);
        setServiceImplName(className+ SuffixManager.serviceImplSuffix);
        setDaoName(className+ SuffixManager.daoSuffix);
        setMapperName(className+ SuffixManager.mapperSuffix);
        setUtilsName(className+ SuffixManager.utilSuffix);


        //path
        basePath= StringUtils4V.systemPath2JavaPackagePath(basePath);

        if (basePath.startsWith(".")){
            basePath= basePath.substring(1);
        }

        setDoPath(basePath+"model.d_o."+className+ SuffixManager.dOSuffix);
        setDtoPath(basePath+"model.dto."+className+ SuffixManager.dTOSuffix);
        setVoPath(basePath+"model.vo."+className+ SuffixManager.vOSuffix);

        setControllerPath(basePath+"controller."+className+ SuffixManager.controllerSuffix);
        setServiceIPath(basePath+"serviceI."+className+ SuffixManager.serviceISuffix);
        setServiceImplPath(basePath+"serviceI.serviceImpl."+className+ SuffixManager.serviceImplSuffix);
        setDaoPath(basePath+"dao."+className+ SuffixManager.daoSuffix);
        setMapperPath(basePath+"mapper."+className+ SuffixManager.mapperSuffix);
        setUtilsPath(basePath+"utils."+className+ SuffixManager.utilSuffix);


        setDoPackage("package "+basePath+"model.d_o;");
        setDtoPackage("package "+basePath+"model.dto;");
        setVoPackage("package "+basePath+"model.vo;");

        setControllerPackage("package "+basePath+"controller;");
        setServiceIPackage("package "+basePath+"serviceI;");
        setServiceImplPackage("package "+basePath+"serviceI.serviceImpl;");
        setDaoPackage("package "+basePath+"dao;");
        setMapperPackage("package "+basePath+"mapper;");
        setUtilsPackage("package "+basePath+"utils;");


        setDoBase(basePath+"model.d_o");
        setDtoBase(basePath+"model.dto");
        setVoBase(basePath+"model.vo");

        setControllerBase(basePath+"controller");
        setServiceIBase(basePath+"serviceI");
        setServiceImplBase(basePath+"serviceI.serviceImpl");
        setDaoBase(basePath+"dao");
        setMapperBase(basePath+"mapper");
        setUtilsBase(basePath+"utils");

    }
}
