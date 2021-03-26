package build;

import build.utils.FreemarkerTemplateBuilder;
import freemarker.template.Template;

import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 */
public class BuildTest {


    public static void createViewObject() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("view_object.ftl");
        ObjectClassDescription objectDescription = new ObjectClassDescription();
        objectDescription.setBelongPackage("com.view_object;");
        objectDescription.setShortName("Test");
        InnerAttributeDescription innerAttributeDescription = new InnerAttributeDescription();
        innerAttributeDescription.setType("Integer");
        innerAttributeDescription.setName("userAge");
        objectDescription.addInnerAttributeDescription(innerAttributeDescription);
        Writer out = new OutputStreamWriter(System.out);

        template.process(objectDescription, out);
    }


    public static void createDataTransferObject() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("data_transfer_object.ftl");
        ObjectClassDescription objectDescription = new ObjectClassDescription();
        objectDescription.setBelongPackage("com.transfer_object;");
        objectDescription.setShortName("Test");
        InnerAttributeDescription innerAttributeDescription = new InnerAttributeDescription();
        innerAttributeDescription.setType("Integer");
        innerAttributeDescription.setName("userAge");
        objectDescription.addInnerAttributeDescription(innerAttributeDescription);
        Writer out = new OutputStreamWriter(System.out);
        template.process(objectDescription, out);
    }

    public static void createDomainObject() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("domain_object.ftl");
        ObjectClassDescription objectDescription = new ObjectClassDescription();
        objectDescription.setBelongPackage("com.domain_object;");
        objectDescription.setShortName("Test");
        InnerAttributeDescription innerAttributeDescription = new InnerAttributeDescription();
        innerAttributeDescription.setType("Integer");
        innerAttributeDescription.setName("userAge");
        objectDescription.addInnerAttributeDescription(innerAttributeDescription);
        Writer out = new OutputStreamWriter(System.out);
        template.process(objectDescription, out);
    }

    public static void createDao() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("dao.ftl");
        DaoClassDescription daoDescription = new DaoClassDescription();
        daoDescription.addDependent("import java.lang.Integer;");
        daoDescription.addDependent("import java.lang.Long;");
        daoDescription.setBelongPackage("com.dao;");
        daoDescription.setShortName("TestDao");
        daoDescription.addInterfaceMethods(new InnerMethodDescription("public void saveData();"));
        daoDescription.addInterfaceMethods(new InnerMethodDescription("public List<T> listData();"));
        Writer out = new OutputStreamWriter(System.out);
        template.process(daoDescription, out);
    }

    public static void createServiceImpl() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("impl.ftl");
        ServiceImplClassDescription serviceImplDescription = new ServiceImplClassDescription();
        serviceImplDescription.setBelongPackage("com.serviceImpl;");
        serviceImplDescription.setShortName("TestServiceImpl");
        serviceImplDescription.setInterfaceName("TestServiceI");
        serviceImplDescription.setInnerDao("TestDao");
        serviceImplDescription.addOverWriteMethods(new InnerMethodDescription("public void saveData()"));
        serviceImplDescription.addOverWriteMethods(new InnerMethodDescription("public List<T> listData()"));
        Writer out = new OutputStreamWriter(System.out);
        template.process(serviceImplDescription, out);
    }

    public static void createServiceI() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("serviceI.ftl");
        ServiceIClassDescription serviceIDescription = new ServiceIClassDescription();
        serviceIDescription.setBelongPackage("com.serviceI;");
        serviceIDescription.setShortName("TestServiceI");
        serviceIDescription.addInterfaceMethods(new InnerMethodDescription("public void saveData();"));
        serviceIDescription.addInterfaceMethods(new InnerMethodDescription("public List<T> listData();"));
        Writer out = new OutputStreamWriter(System.out);
        template.process(serviceIDescription, out);
    }

    public static void createController() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("controller.ftl");
        ControllerClassDescription controllerDescription = new ControllerClassDescription();
        controllerDescription.setBelongPackage("com.controller;");
        controllerDescription.setBaseMapping("/test");
        controllerDescription.setShortName("TestController");
        controllerDescription.setInnerService("TestServiceI");
        controllerDescription.addMappingMethod(new InnerMethodDescription("/listData", "listData"));
        controllerDescription.addMappingMethod(new InnerMethodDescription("/saveData", "saveData"));
        Writer out = new OutputStreamWriter(System.out);
        template.process(controllerDescription, out);
    }
}
