//package build;
//
//import build.adapter.TypeConverter;
//import build.data_base.DBColumnModel;
//import build.data_base.DBTableModel;
//import build.utils.FreemarkerTemplateBuilder;
//import build.utils.StringUtils4V;
//import freemarker.template.Template;
//
//import org.apache.commons.io.FileUtils;
//
//import java.io.File;
//import java.io.FileOutputStream;
//
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Pattern;
//
//public class ProjectBuilder {
//    public static final String MYBATIS_FRAME="mybatis_";
//    public static final String JPA_FRAME="jpa_";
//
//    private List<DirectDescription> baseDirect = new ArrayList<>();
//    private List<DBTableModel> dbTableModels;
//    private String controllerPath;
//    private String serviceIPath;
//    private String serviceImplPath;
//    private String daoPath;
//    private String doPath;
//    private String dtoPath;
//    private String voPath;
//    private String utilsPath;
//    private String mapperPath;
//
//    //default value
//    private String before = "com";
//    private String dbFrameType="";
//
//
//    /**
//     * @param path
//     * @param absolutePath
//     * @param templateName
//     * @param baseClassDescriptions 领域对象集合
//     * @throws Exception
//     */
//    private void createFileByTemplate(String path, String absolutePath, String templateName, List<BaseClassDescription> baseClassDescriptions) throws Exception {
//        DirectDescription controller = new DirectDescription(path, absolutePath, baseClassDescriptions);
//        Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
//        List<BaseClassDescription> list = controller.getList();
//        list.forEach(x -> {
//            FileOutputStream fileOutputStream = null;
//            try {
//                fileOutputStream = FileUtils.openOutputStream(new File(controller.getAbsolutePath() + File.separator + x.getFullName() + ".java"));
//                template.process(x, new OutputStreamWriter(fileOutputStream));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//
//    }
//
//
//    private void createFileByTemplate2(String path, String absolutePath, String templateName,List<DBTableModel> list) throws Exception {
//        Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
//        list.forEach(x -> {
//            FileOutputStream fileOutputStream = null;
//            try {
//                fileOutputStream = FileUtils.openOutputStream(new File(absolutePath + File.separator + x.getTableName() + ".xml"));
//                template.process(x, new OutputStreamWriter(fileOutputStream));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//
//    }
//
//
//    public void build(List<DBTableModel> dbTableModels, String basePath) throws Exception {
//        this.dbTableModels = dbTableModels;
//
//        if (!basePath.endsWith(File.separator)) {
//            basePath += File.separator;
//        }
//
//        controllerPath = before + File.separator + "controller";
//        serviceIPath = before + File.separator + "serviceI";
//        serviceImplPath = before + File.separator + "serviceI" + File.separator + "impl";
//        daoPath = before + File.separator + "dao";
//        doPath = before + File.separator + "model" + File.separator + "domain_object";
//        dtoPath = before + File.separator + "model" + File.separator + "transfer_object";
//        voPath = before + File.separator + "model" + File.separator + "view_object";
//        utilsPath = before + File.separator + "utils";
//        mapperPath=before + File.separator + "mapper";
//
////        dbTableModels.forEach(x->{
////            x.setDoPath(("import " + doPath + File.separator + x.getName() + FreemarkerTemplateBuilder.suffixManager.getdOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
////            x.setDtoPath(("import " + dtoPath + File.separator + x.getName() + FreemarkerTemplateBuilder.suffixManager.getdOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
////            x.setVoPath(("import " + voPath + File.separator + x.getName() + FreemarkerTemplateBuilder.suffixManager.getdOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
////            x.setDaoPath(("import " + daoPath + File.separator + x.getName() + FreemarkerTemplateBuilder.suffixManager.getdOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
////        });
//
//
//        createFileByTemplate(controllerPath, basePath + controllerPath, dbFrameType+"controller.ftl", getControllerElement(controllerPath));
//
//        createFileByTemplate(serviceIPath, basePath + serviceIPath, dbFrameType+"serviceI.ftl", getServiceIElement(serviceIPath));
//
//        createFileByTemplate(serviceImplPath, basePath + serviceImplPath, dbFrameType+"serviceImpl.ftl", getServiceImplElement(serviceImplPath));
//
//        createFileByTemplate(daoPath, basePath + daoPath, dbFrameType+"dao.ftl", getDaoElement(daoPath));
//
//        createFileByTemplate(doPath, basePath + doPath, dbFrameType+"domain_object.ftl", getDOElement(doPath));
//
//        createFileByTemplate(dtoPath, basePath + dtoPath, "data_transfer_object.ftl", getDTOElement(dtoPath));
//
//        createFileByTemplate(voPath, basePath + voPath, "view_object.ftl", getVOElement(voPath));
//
//        createFileByTemplate2(mapperPath, basePath + mapperPath, "mapper.ftl", dbTableModels);
//
//        List<BaseClassDescription> baseClassDescriptions = new ArrayList<>();
//
//        BaseClassDescription baseClassDescription = new BaseClassDescription();
//        baseClassDescription.setBelongPackage("package " + utilsPath.replaceAll(Pattern.quote(File.separator), ".") + ";");
//        baseClassDescription.setShortName("GeneralResponseObject");
//        baseClassDescription.setFullName("GeneralResponseObject");
//        baseClassDescriptions.add(baseClassDescription);
//        createFileByTemplate(utilsPath, basePath + utilsPath, "general_esponse_object.ftl", baseClassDescriptions);
//
//        List<BaseClassDescription> baseClassDescriptions2 = new ArrayList<>();
//        BaseClassDescription baseClassDescription2 = new BaseClassDescription();
//        baseClassDescription2.setBelongPackage("package " + utilsPath.replaceAll(Pattern.quote(File.separator), ".") + ";");
//        baseClassDescription2.setShortName("BaseObject");
//        baseClassDescription2.setFullName("BaseObject");
//        baseClassDescriptions2.add(baseClassDescription2);
//        createFileByTemplate(utilsPath, basePath + utilsPath, "base_object.ftl", baseClassDescriptions2);
//
//
//    }
//
//
//    private List<BaseClassDescription> getUtilsElement(String path) {
//        List<BaseClassDescription> list = new ArrayList<>();
//        return list;
//    }
//
//
//    /**
//     * vo template create
//     *
//     * @param path
//     * @return
//     */
//    private List<BaseClassDescription> getVOElement(String path) {
//        List<BaseClassDescription> list = new ArrayList<>();
//        dbTableModels.forEach(x -> {
//            //todo 单个对象
//            List<DBColumnModel> dbColumnModelList = x.getDbColumnModelList();
//            ObjectClassDescription objectClassDescription = new ObjectClassDescription();
//            objectClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
//            objectClassDescription.setShortName(x.getTableName());
//            objectClassDescription.setFullName(x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getvOSuffix());
//            dbColumnModelList.forEach(y -> {
//                InnerAttributeDescription innerAttributeDescription = new InnerAttributeDescription();
//                innerAttributeDescription.setExtra(y.getExtra());
//                innerAttributeDescription.setColumnKey(y.getColumnKey());
//                innerAttributeDescription.setComment(y.getComment());
//                innerAttributeDescription.setName(y.getName());
//                innerAttributeDescription.setType(TypeConverter.covert(y.getType()));
//                objectClassDescription.addInnerAttributeDescription(innerAttributeDescription);
//            });
//            list.add(objectClassDescription);
//        });
//        return list;
//    }
//
//    /**
//     * dto template create
//     *
//     * @param path
//     * @return
//     */
//    private List<BaseClassDescription> getDTOElement(String path) {
//        List<BaseClassDescription> list = new ArrayList<>();
//        dbTableModels.forEach(x -> {
//            //todo 单个对象
//            List<DBColumnModel> dbColumnModelList = x.getDbColumnModelList();
//            ObjectClassDescription objectClassDescription = new ObjectClassDescription();
//            objectClassDescription.addDependent(("import " + doPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getdOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            objectClassDescription.addDependent(("import " + voPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getvOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            objectClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
//            objectClassDescription.setShortName(x.getTableName());
//            objectClassDescription.setFullName(x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix());
//            dbColumnModelList.forEach(y -> {
//                InnerAttributeDescription innerAttributeDescription = new InnerAttributeDescription();
//                innerAttributeDescription.setExtra(y.getExtra());
//                innerAttributeDescription.setColumnKey(y.getColumnKey());
//                innerAttributeDescription.setComment(y.getComment());
//                innerAttributeDescription.setName(y.getName());
//                innerAttributeDescription.setType(TypeConverter.covert(y.getType()));
//                objectClassDescription.addInnerAttributeDescription(innerAttributeDescription);
//            });
//            list.add(objectClassDescription);
//        });
//        return list;
//    }
//
//    /**
//     * do template create
//     *
//     * @param path
//     * @return
//     */
//    private List<BaseClassDescription> getDOElement(String path) {
//        List<BaseClassDescription> list = new ArrayList<>();
//        dbTableModels.forEach(x -> {
//            //todo 单个对象
//            List<DBColumnModel> dbColumnModelList = x.getDbColumnModelList();
//            ObjectClassDescription objectClassDescription = new ObjectClassDescription();
//            objectClassDescription.addDependent(("import " + dtoPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            objectClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
//            objectClassDescription.setShortName(x.getTableName());
//            objectClassDescription.setFullName(x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getdOSuffix());
//            dbColumnModelList.forEach(y -> {
//                InnerAttributeDescription innerAttributeDescription = new InnerAttributeDescription();
//                innerAttributeDescription.setExtra(y.getExtra());
//                innerAttributeDescription.setColumnKey(y.getColumnKey());
//                innerAttributeDescription.setComment(y.getComment());
//                innerAttributeDescription.setName(y.getName());
//                innerAttributeDescription.setType(TypeConverter.covert(y.getType()));
//                objectClassDescription.addInnerAttributeDescription(innerAttributeDescription);
//            });
//            list.add(objectClassDescription);
//        });
//        return list;
//    }
//
//    /**
//     * controller template create
//     *
//     * @param path
//     * @return
//     */
//    private List<BaseClassDescription> getControllerElement(String path) {
//        List<BaseClassDescription> list = new ArrayList<>();
//        dbTableModels.forEach(x -> {
//            //todo 单个对象
//            ControllerClassDescription controllerClassDescription = new ControllerClassDescription();
//            controllerClassDescription.addDependent(("import " + utilsPath + File.separator + "GeneralResponseObject;").replaceAll(Pattern.quote(File.separator), "."));
//            controllerClassDescription.addDependent(("import " + voPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getvOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            controllerClassDescription.addDependent(("import " + dtoPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            controllerClassDescription.addDependent(("import " + serviceIPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getServiceISuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            controllerClassDescription.setInnerService(x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getServiceISuffix());
//            controllerClassDescription.setBaseMapping("/" + StringUtils4V.lowercaseFirstLetter(x.getTableName()));
//            controllerClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
//            //class Name
//            String name = x.getTableName();
//            controllerClassDescription.setShortName(name);
//            controllerClassDescription.setFullName(name + FreemarkerTemplateBuilder.suffixManager.getControllerSuffix());
//
//            StructureConstant.methodTemplate.forEach(y -> {
//                InnerMethodDescription innerMethodDescription = new InnerMethodDescription();
//                String returnStr = "void ";
//                String inputStr = "";
//                if (y.hasInput()) {
//                    inputStr = name + " " + name.toLowerCase();
//                }
//                if (y.hasReturn()) {
//                    returnStr = name + " ";
//                }
//                innerMethodDescription.setDescription("public " + returnStr + y.getName() + name + "(" + inputStr + ")");
//                innerMethodDescription.setName(y.getName() + name);
//                innerMethodDescription.setPath("/" + y.getName() + name);
//                innerMethodDescription.setHasReturn(y.hasReturn());
//                controllerClassDescription.addMappingMethod(innerMethodDescription);
//            });
//            list.add(controllerClassDescription);
//        });
//        return list;
//    }
//
//    /**
//     * dao template create
//     *
//     * @param path
//     * @return
//     */
//    private List<BaseClassDescription> getDaoElement(String path) {
//        List<BaseClassDescription> list = new ArrayList<>();
//        dbTableModels.forEach(x -> {
//            //todo 单个对象
//            DaoClassDescription daoClassDescription = new DaoClassDescription();
//            daoClassDescription.addDependent(("import " + doPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getdOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            daoClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
//            //class Name
//            String name = x.getTableName();
//            daoClassDescription.setShortName(name);
//            daoClassDescription.setFullName(name + FreemarkerTemplateBuilder.suffixManager.getDaoSuffix());
//
//            StructureConstant.methodTemplate.forEach(y -> {
//                InnerMethodDescription innerMethodDescription = new InnerMethodDescription();
//                String returnStr = "void ";
//                String inputStr = "";
//                if (y.hasInput()) {
//                    inputStr = name + FreemarkerTemplateBuilder.suffixManager.getdOSuffix() + " " + StringUtils4V.lowercaseFirstLetter(name) + FreemarkerTemplateBuilder.suffixManager.getdOSuffix();
//                }
//                if (y.hasReturn()) {
//                    returnStr = name + FreemarkerTemplateBuilder.suffixManager.getdOSuffix() + " ";
//                }
//                innerMethodDescription.setDescription("public " + returnStr + y.getName() + name + "(" + inputStr + ")");
//                innerMethodDescription.setName(y.getName());
//                innerMethodDescription.setHasReturn(y.hasReturn());
//                daoClassDescription.addInterfaceMethods(innerMethodDescription);
//            });
//            list.add(daoClassDescription);
//        });
//        return list;
//    }
//
//    /**
//     * service implement template create
//     *
//     * @param path
//     * @return
//     */
//    private List<BaseClassDescription> getServiceImplElement(String path) {
//        List<BaseClassDescription> list = new ArrayList<>();
//        dbTableModels.forEach(x -> {
//            //todo 单个对象
//            ServiceImplClassDescription serviceImplClassDescription = new ServiceImplClassDescription();
//            serviceImplClassDescription.addDependent(("import " + doPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getdOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            serviceImplClassDescription.addDependent(("import " + daoPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getDaoSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            serviceImplClassDescription.addDependent(("import " + dtoPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            serviceImplClassDescription.addDependent(("import " + serviceIPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getServiceISuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            serviceImplClassDescription.setInnerDao(x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getDaoSuffix());
//            serviceImplClassDescription.setInterfaceName(x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getServiceISuffix());
//            serviceImplClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
//            //class Name
//            String name = x.getTableName();
//            serviceImplClassDescription.setShortName(name);
//            serviceImplClassDescription.setFullName(name + FreemarkerTemplateBuilder.suffixManager.getServiceImplSuffix());
//            StructureConstant.methodTemplate.forEach(y -> {
//                InnerMethodDescription innerMethodDescription = new InnerMethodDescription();
//                String returnStr = "void ";
//                String inputStr = "";
//                if (y.hasInput()) {
//                    inputStr = name + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix() + " " + StringUtils4V.lowercaseFirstLetter(name) + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix();
//                }
//                if (y.hasReturn()) {
//                    returnStr = name + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix() + " ";
//                }
//                innerMethodDescription.setDescription("public " + returnStr + y.getName() + name + "(" + inputStr + ")");
//                innerMethodDescription.setName(y.getName());
//                innerMethodDescription.setHasReturn(y.hasReturn());
//                serviceImplClassDescription.addOverWriteMethods(innerMethodDescription);
//            });
//            list.add(serviceImplClassDescription);
//        });
//        return list;
//    }
//
//    /**
//     * service interface template create
//     *
//     * @param path
//     * @return
//     */
//    private List<BaseClassDescription> getServiceIElement(String path) {
//        List<BaseClassDescription> list = new ArrayList<>();
//        dbTableModels.forEach(x -> {
//            //todo 单个对象
//            ServiceIClassDescription serviceIClassDescription = new ServiceIClassDescription();
//            serviceIClassDescription.addDependent(("import " + dtoPath + File.separator + x.getTableName() + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix() + ";").replaceAll(Pattern.quote(File.separator), "."));
//            serviceIClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
//            //class Name
//            String name = x.getTableName();
//            serviceIClassDescription.setShortName(name);
//            serviceIClassDescription.setFullName(name + FreemarkerTemplateBuilder.suffixManager.getServiceISuffix());
//            StructureConstant.methodTemplate.forEach(y -> {
//                InnerMethodDescription innerMethodDescription = new InnerMethodDescription();
//                String returnStr = "void ";
//                String inputStr = "";
//                if (y.hasInput()) {
//                    inputStr = name + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix() + " " + StringUtils4V.lowercaseFirstLetter(name) + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix();
//                }
//                if (y.hasReturn()) {
//                    returnStr = name + FreemarkerTemplateBuilder.suffixManager.getdTOSuffix() + " ";
//                }
//                innerMethodDescription.setDescription("public " + returnStr + y.getName() + name + "(" + inputStr + ")");
//                innerMethodDescription.setName(y.getName());
//                innerMethodDescription.setHasReturn(y.hasReturn());
//                serviceIClassDescription.addInterfaceMethods(innerMethodDescription);
//            });
//            list.add(serviceIClassDescription);
//        });
//        return list;
//    }
//
//    public void setBefore(String before) {
//        this.before = before;
//    }
//
//    public void setDbFrameType(String dbFrameType) {
//        this.dbFrameType = dbFrameType;
//    }
//}
