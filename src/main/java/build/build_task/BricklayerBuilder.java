package build.build_task;

import build.java_bean.JavaBeanModel;

import java.util.ArrayList;
import java.util.List;

public class BricklayerBuilder {
    private   static List<JavaBuildTask> list=new ArrayList<>();

    static {
        list.add(new VOBuildTask());
        list.add(new DTOBuildTask());
        list.add(new DOBuildTask());
        list.add(new ControllerBuildTask());
        list.add(new ServiceIBuildTask());
        list.add(new ServiceImplBuildTask());
        list.add(new DaoBuildTask());
        list.add(new MapperBuildTask());
        list.add(new UtilsBuildTask());

    }

    public static void  build(List<JavaBeanModel> javaBeanModels,String outPutPath){
        list.forEach(x->{
            x.build(javaBeanModels,outPutPath);
        });
    }
}
