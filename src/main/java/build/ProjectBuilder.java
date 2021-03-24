package build;

import build.adapter.TypeConverter;
import build.data_bases.DBColumnModel;
import build.data_bases.DBTableModel;
import freemarker.template.Template;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProjectBuilder {
    private List<DirectDescription> baseDirect = new ArrayList<>();
    private List<DBTableModel> dbTableModels;
    private String controllerPath;
    private String serviceIPath;
    private String serviceImplPath;
    private String daoPath;
    private String doPath;
    private String dtoPath;
    private String voPath;
    private String utilsPath;
    private String before ;


    public static void main(String[] args) {
        List<DBTableModel> dbTableModels = new ArrayList<>();
        List<DBColumnModel> dbColumnModelList = new ArrayList();
        DBColumnModel dbColumnModel = new DBColumnModel();
        dbColumnModel.setName("id");
        dbColumnModel.setType("int");
        dbColumnModelList.add(dbColumnModel);

        DBColumnModel dbColumnModel2 = new DBColumnModel();
        dbColumnModel2.setName("name");
        dbColumnModel2.setType("varchar");
        dbColumnModelList.add(dbColumnModel2);

        DBTableModel dbTableModel = new DBTableModel();
        dbTableModel.setName("User");
        dbTableModel.setDbColumnModelList(dbColumnModelList);
        dbTableModels.add(dbTableModel);
        ProjectBuilder projectBuilder = new ProjectBuilder();
        String basePath = "D:\\JAVA_WORK_SPACE\\bricklayer\\src\\main\\java\\";
        try {
            projectBuilder.build(dbTableModels, basePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path
     * @param absolutePath
     * @param templateName
     * @param baseClassDescriptions 领域对象集合
     * @throws Exception
     */
    private void createFileByTemplate(String path, String absolutePath, String templateName, List<BaseClassDescription> baseClassDescriptions) throws Exception {
        DirectDescription controller = new DirectDescription(path, absolutePath, baseClassDescriptions);
        Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
        List<BaseClassDescription> list = controller.getList();
        list.forEach(x -> {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = FileUtils.openOutputStream(new File(controller.getAbsolutePath() + File.separator + x.getFullName() + ".java"));
                template.process(x, new OutputStreamWriter(fileOutputStream));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });



    }

    public void build(List<DBTableModel> dbTableModels, String basePath) throws Exception {
        this.dbTableModels = dbTableModels;

        if (!basePath.endsWith(File.separator)) {
            basePath += File.separator;
        }

        before = "com";
        controllerPath = before + File.separator + "controller";
        serviceIPath = before + File.separator + "serviceI";
        serviceImplPath = before + File.separator + "serviceI" + File.separator + "impl";
        daoPath = before + File.separator + "dao";
        doPath = before + File.separator + "model" + File.separator + "domain_object";
        dtoPath = before + File.separator + "model" + File.separator + "transfer_object";
        voPath = before + File.separator + "model" + File.separator + "view_object";
        utilsPath = before + File.separator + "utils";

        createFileByTemplate(controllerPath, basePath + controllerPath, "controller.ftl", getControllerElement(controllerPath));

        createFileByTemplate(serviceIPath, basePath + serviceIPath, "serviceI.ftl", getServiceIElement(serviceIPath));

        createFileByTemplate(serviceImplPath, basePath + serviceImplPath, "serviceImpl.ftl", getServiceImplElement(serviceImplPath));

        createFileByTemplate(daoPath, basePath + daoPath, "dao.ftl", getDaoElement(daoPath));

        createFileByTemplate(doPath, basePath + doPath, "domain_object.ftl", getDOElement(doPath));

        createFileByTemplate(dtoPath, basePath + dtoPath, "data_transfer_object.ftl", getDTOElement(dtoPath));

        createFileByTemplate(voPath, basePath + voPath, "view_object.ftl", getVOElement(voPath));

        List<BaseClassDescription> baseClassDescriptions=new ArrayList<>();
        BaseClassDescription baseClassDescription = new BaseClassDescription();
        baseClassDescription.setBelongPackage("package " + utilsPath.replaceAll(Pattern.quote(File.separator), ".") + ";");
        baseClassDescription.setShortName("GeneralResponseObject");
        baseClassDescription.setFullName("GeneralResponseObject");
        baseClassDescriptions.add(baseClassDescription);
        createFileByTemplate(utilsPath, basePath + utilsPath, "general_esponse_object.ftl", baseClassDescriptions);


    }


    private List<BaseClassDescription> getUtilsElement(String path) {
        List<BaseClassDescription> list = new ArrayList<>();

        return list;
    }


    private List<BaseClassDescription> getVOElement(String path) {
        List<BaseClassDescription> list = new ArrayList<>();
        dbTableModels.forEach(x -> {
            //todo 单个对象
            List<DBColumnModel> dbColumnModelList = x.getDbColumnModelList();
            ObjectClassDescription objectClassDescription = new ObjectClassDescription();
            objectClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
            objectClassDescription.setShortName(x.getName());
            objectClassDescription.setFullName(x.getName() + "VO");
            dbColumnModelList.forEach(y -> {
                InnerAttributeDescription innerAttributeDescription = new InnerAttributeDescription();
                innerAttributeDescription.setName(y.getName());
                innerAttributeDescription.setType(TypeConverter.covert(y.getType()));
                objectClassDescription.addInnerAttributeDescription(innerAttributeDescription);
            });
            list.add(objectClassDescription);
        });
        return list;
    }


    private List<BaseClassDescription> getDTOElement(String path) {
        List<BaseClassDescription> list = new ArrayList<>();
        dbTableModels.forEach(x -> {
            //todo 单个对象
            List<DBColumnModel> dbColumnModelList = x.getDbColumnModelList();
            ObjectClassDescription objectClassDescription = new ObjectClassDescription();
            objectClassDescription.addDependent(("import "+doPath+File.separator+x.getName()+"DO;").replaceAll(Pattern.quote(File.separator), "."));
            objectClassDescription.addDependent(("import "+voPath+File.separator+x.getName()+"VO;").replaceAll(Pattern.quote(File.separator), "."));
            objectClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
            objectClassDescription.setShortName(x.getName());
            objectClassDescription.setFullName(x.getName() + "DTO");
            dbColumnModelList.forEach(y -> {
                InnerAttributeDescription innerAttributeDescription = new InnerAttributeDescription();
                innerAttributeDescription.setName(y.getName());
                innerAttributeDescription.setType(TypeConverter.covert(y.getType()));
                objectClassDescription.addInnerAttributeDescription(innerAttributeDescription);
            });
            list.add(objectClassDescription);
        });
        return list;
    }


    private List<BaseClassDescription> getDOElement(String path) {
        List<BaseClassDescription> list = new ArrayList<>();
        dbTableModels.forEach(x -> {
            //todo 单个对象
            List<DBColumnModel> dbColumnModelList = x.getDbColumnModelList();
            ObjectClassDescription objectClassDescription = new ObjectClassDescription();
            objectClassDescription.addDependent(("import "+dtoPath+File.separator+x.getName()+"DTO;").replaceAll(Pattern.quote(File.separator), "."));
            objectClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
            objectClassDescription.setShortName(x.getName());
            objectClassDescription.setFullName(x.getName() + "DO");
            dbColumnModelList.forEach(y -> {
                InnerAttributeDescription innerAttributeDescription = new InnerAttributeDescription();
                innerAttributeDescription.setName(y.getName());
                innerAttributeDescription.setType(TypeConverter.covert(y.getType()));
                objectClassDescription.addInnerAttributeDescription(innerAttributeDescription);
            });
            list.add(objectClassDescription);
        });
        return list;
    }


    private List<BaseClassDescription> getControllerElement(String path) {
        List<BaseClassDescription> list = new ArrayList<>();
        dbTableModels.forEach(x -> {
            //todo 单个对象
            ControllerClassDescription controllerClassDescription = new ControllerClassDescription();
            controllerClassDescription.addDependent(("import "+utilsPath+File.separator+"GeneralResponseObject;").replaceAll(Pattern.quote(File.separator), "."));
            controllerClassDescription.addDependent(("import "+voPath+File.separator+x.getName()+"VO;").replaceAll(Pattern.quote(File.separator), "."));
            controllerClassDescription.addDependent(("import "+dtoPath+File.separator+x.getName()+"DTO;").replaceAll(Pattern.quote(File.separator), "."));
            controllerClassDescription.addDependent(("import "+serviceIPath+File.separator+x.getName()+"ServiceI;").replaceAll(Pattern.quote(File.separator), "."));
            controllerClassDescription.setInnerService(x.getName() + "ServiceI");
            controllerClassDescription.setBaseMapping("/" + x.getName().toLowerCase());
            controllerClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
            //class Name
            String name = x.getName();
            controllerClassDescription.setShortName(name );
            controllerClassDescription.setFullName(name + "Controller");

            StructureConstant.methodTemplate.forEach(y -> {
                InnerMethodDescription innerMethodDescription = new InnerMethodDescription();
                String returnStr = "void ";
                String inputStr = "";
                if (y.hasInput()) {
                    inputStr = name + " " + name.toLowerCase();
                }
                if (y.hasReturn()) {
                    returnStr = name + " ";
                }
                innerMethodDescription.setDescription("public " + returnStr + y.getName() + name + "(" + inputStr + ")");
                innerMethodDescription.setName(y.getName() + name);
                innerMethodDescription.setPath("/" + y.getName() + name);
                innerMethodDescription.setHasReturn(y.hasReturn());
                controllerClassDescription.addMappingMethod(innerMethodDescription);
            });
            list.add(controllerClassDescription);
        });
        return list;
    }

    private List<BaseClassDescription> getDaoElement(String path) {
        List<BaseClassDescription> list = new ArrayList<>();
        dbTableModels.forEach(x -> {
            //todo 单个对象
            DaoClassDescription daoClassDescription = new DaoClassDescription();
            daoClassDescription.addDependent(("import "+doPath+File.separator+x.getName()+"DO;").replaceAll(Pattern.quote(File.separator), "."));
            daoClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
            //class Name
            String name = x.getName();
            daoClassDescription.setShortName(name );
            daoClassDescription.setFullName(name + "Dao");

            StructureConstant.methodTemplate.forEach(y -> {
                InnerMethodDescription innerMethodDescription = new InnerMethodDescription();
                String returnStr = "void ";
                String inputStr = "";
                if (y.hasInput()) {
                    inputStr = name + "DO " + name.toLowerCase();
                }
                if (y.hasReturn()) {
                    returnStr = name + "DO ";
                }
                innerMethodDescription.setDescription("public " + returnStr + y.getName() + name + "(" + inputStr + ")");
                innerMethodDescription.setName(y.getName());
                innerMethodDescription.setHasReturn(y.hasReturn());
                daoClassDescription.addInterfaceMethods(innerMethodDescription);
            });
            list.add(daoClassDescription);
        });
        return list;
    }

    private List<BaseClassDescription> getServiceImplElement(String path) {
        List<BaseClassDescription> list = new ArrayList<>();
        dbTableModels.forEach(x -> {
            //todo 单个对象
            ServiceImplClassDescription serviceImplClassDescription = new ServiceImplClassDescription();
            serviceImplClassDescription.addDependent(("import "+doPath+File.separator+x.getName()+"DO;").replaceAll(Pattern.quote(File.separator), "."));
            serviceImplClassDescription.addDependent(("import "+daoPath+File.separator+x.getName()+"Dao;").replaceAll(Pattern.quote(File.separator), "."));
            serviceImplClassDescription.addDependent(("import "+dtoPath+File.separator+x.getName()+"DTO;").replaceAll(Pattern.quote(File.separator), "."));
            serviceImplClassDescription.addDependent(("import "+serviceIPath+File.separator+x.getName()+"ServiceI;").replaceAll(Pattern.quote(File.separator), "."));
            serviceImplClassDescription.setInnerDao(x.getName() + "Dao");
            serviceImplClassDescription.setInterfaceName(x.getName() + "ServiceI");
            serviceImplClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
            //class Name
            String name = x.getName();
            serviceImplClassDescription.setShortName(name );
            serviceImplClassDescription.setFullName(name + "ServiceImpl");
            StructureConstant.methodTemplate.forEach(y -> {
                InnerMethodDescription innerMethodDescription = new InnerMethodDescription();
                String returnStr = "void ";
                String inputStr = "";
                if (y.hasInput()) {
                    inputStr = name + "DTO " + name.toLowerCase()+"DTO";
                }
                if (y.hasReturn()) {
                    returnStr = name + "DTO ";
                }
                innerMethodDescription.setDescription("public " + returnStr + y.getName() + name + "(" + inputStr + ")");
                innerMethodDescription.setName(y.getName());
                innerMethodDescription.setHasReturn(y.hasReturn());
                serviceImplClassDescription.addOverWriteMethods(innerMethodDescription);
            });
            list.add(serviceImplClassDescription);
        });
        return list;
    }

    private List<BaseClassDescription> getServiceIElement(String path) {
        List<BaseClassDescription> list = new ArrayList<>();
        dbTableModels.forEach(x -> {
            //todo 单个对象
            ServiceIClassDescription serviceIClassDescription = new ServiceIClassDescription();
            serviceIClassDescription.addDependent(("import "+dtoPath+File.separator+x.getName()+"DTO;").replaceAll(Pattern.quote(File.separator), "."));
            serviceIClassDescription.setBelongPackage("package " + path.replaceAll(Pattern.quote(File.separator), ".") + ";");
            //class Name
            String name = x.getName();
            serviceIClassDescription.setShortName(name );
            serviceIClassDescription.setFullName(name + "ServiceI");
            StructureConstant.methodTemplate.forEach(y -> {
                InnerMethodDescription innerMethodDescription = new InnerMethodDescription();
                String returnStr = "void ";
                String inputStr = "";
                if (y.hasInput()) {
                    inputStr = name + "DTO " + name.toLowerCase()+"DTO";
                }
                if (y.hasReturn()) {
                    returnStr = name + "DTO ";
                }
                innerMethodDescription.setDescription("public " + returnStr + y.getName() + name + "(" + inputStr + ")");
                innerMethodDescription.setName(y.getName());
                innerMethodDescription.setHasReturn(y.hasReturn());
                serviceIClassDescription.addInterfaceMethods(innerMethodDescription);
            });
            list.add(serviceIClassDescription);
        });
        return list;
    }



}
