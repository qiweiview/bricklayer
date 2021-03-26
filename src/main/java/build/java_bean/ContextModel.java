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
    private String dOName;

    private String dTOName;

    private String vOName;


    private String controllerName;

    private String serviceIName;

    private String serviceImplName;

    private String daoName;

    private String mapperName;

    private String utilsName;

    //全路径
    private String dOPath;

    private String vOPath;

    private String dTOPath;

    private String controllerPath;

    private String serviceIPath;

    private String serviceImplPath;

    private String daoPath;

    private String mapperPath;

    private String utilsPath;


    public void load(String className, String basePath) {

        //name
        setDOName(className+ SuffixManager.dOSuffix);
        setDTOName(className+ SuffixManager.dTOSuffix);
        setVOName(className+ SuffixManager.vOSuffix);

        setControllerName(className+ SuffixManager.controllerSuffix);
        setServiceIName(className+ SuffixManager.serviceISuffix);
        setServiceImplName(className+ SuffixManager.serviceImplSuffix);
        setDaoName(className+ SuffixManager.daoSuffix);
        setMapperName(className+ SuffixManager.mapperSuffix);
        setUtilsName(className+ SuffixManager.utilSuffix);


        //path
        basePath= StringUtils4V.systemPath2JavaPackagePath(basePath);

        setDOPath(basePath+"model.do."+className+ SuffixManager.dOSuffix);
        setDTOPath(basePath+"model.dto."+className+ SuffixManager.dTOSuffix);
        setVOPath(basePath+"model.vo."+className+ SuffixManager.vOSuffix);

        setControllerPath(basePath+"controller."+className+ SuffixManager.controllerSuffix);
        setServiceIPath(basePath+"serviceI."+className+ SuffixManager.serviceISuffix);
        setServiceImplPath(basePath+"serviceI.serviceImpl."+className+ SuffixManager.serviceImplSuffix);
        setDaoPath(basePath+"dao."+className+ SuffixManager.daoSuffix);
        setMapperPath(basePath+"mapper."+className+ SuffixManager.mapperSuffix);
        setUtilsPath(basePath+"utils."+className+ SuffixManager.utilSuffix);

    }
}
