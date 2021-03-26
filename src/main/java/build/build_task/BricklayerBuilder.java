package build.build_task;

import build.java_bean.JavaBeanModel;

import java.util.ArrayList;
import java.util.List;

public class BricklayerBuilder {
    private   static List<JavaBuildTask> list=new ArrayList<>();

    static {
        list.add(new DOBuildTask());
    }

    public static void  build(List<JavaBeanModel> javaBeanModels,String outPutPath){
        list.forEach(x->{
            x.build(javaBeanModels,outPutPath);
        });
    }
}
