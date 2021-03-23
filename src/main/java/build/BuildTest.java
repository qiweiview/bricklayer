package build;

import freemarker.template.Template;

import java.io.OutputStreamWriter;
import java.io.Writer;


public class BuildTest {
    public static void main(String[] args) throws Exception {
        createDao();
    }

    public static void createObject() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("domain_object.ftl");
        ObjectDescription objectDescription = new ObjectDescription();
        objectDescription.setBelongPackage("com.domain_object;");
        objectDescription.setClassName("TestDo");
        Writer out = new OutputStreamWriter(System.out);
        template.process(objectDescription, out);
    }

    public static void createDao() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("dao.ftl");
        DaoDescription daoDescription = new DaoDescription();
        daoDescription.addDependent("import java.lang.Integer;");
        daoDescription.addDependent("import java.lang.Long;");
        daoDescription.setBelongPackage("com.dao;");
        daoDescription.setClassName("TestDao");
        daoDescription.addInterfaceMethods(new InnerMethodDescription("public void saveData();"));
        daoDescription.addInterfaceMethods(new InnerMethodDescription("public List<T> listData();"));
        Writer out = new OutputStreamWriter(System.out);
        template.process(daoDescription, out);
    }

    public static void createServiceImpl() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("impl.ftl");
        ServiceImplDescription serviceImplDescription = new ServiceImplDescription();
        serviceImplDescription.setBelongPackage("com.serviceImpl;");
        serviceImplDescription.setClassName("TestServiceImpl");
        serviceImplDescription.setInterfaceName("TestServiceI");
        serviceImplDescription.setInnerDao("TestDao");
        serviceImplDescription.addOverWriteMethods(new InnerMethodDescription("public void saveData()"));
        serviceImplDescription.addOverWriteMethods(new InnerMethodDescription("public List<T> listData()"));
        Writer out = new OutputStreamWriter(System.out);
        template.process(serviceImplDescription, out);
    }

    public static void createServiceI() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("serviceI.ftl");
        ServiceIDescription serviceIDescription = new ServiceIDescription();
        serviceIDescription.setBelongPackage("com.serviceI;");
        serviceIDescription.setClassName("TestServiceI");
        serviceIDescription.addInterfaceMethods(new InnerMethodDescription("public void saveData();"));
        serviceIDescription.addInterfaceMethods(new InnerMethodDescription("public List<T> listData();"));
        Writer out = new OutputStreamWriter(System.out);
        template.process(serviceIDescription, out);
    }

    public static void createController() throws Exception{
        Template template = FreemarkerTemplateBuilder.getTemplate("controller.ftl");
        ControllerDescription controllerDescription = new ControllerDescription();
        controllerDescription.setBelongPackage("com.controller;");
        controllerDescription.setBaseMapping("/test");
        controllerDescription.setClassName("TestController");
        controllerDescription.setInnerService("TestServiceI");
        controllerDescription.addMappingMethod(new InnerMethodDescription("/listData", "listData"));
        controllerDescription.addMappingMethod(new InnerMethodDescription("/saveData", "saveData"));
        Writer out = new OutputStreamWriter(System.out);
        template.process(controllerDescription, out);
    }
}
