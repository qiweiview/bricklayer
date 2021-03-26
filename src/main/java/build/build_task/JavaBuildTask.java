package build.build_task;

import build.java_bean.JavaBeanModel;

import java.util.ArrayList;
import java.util.List;

public interface JavaBuildTask {



    public  void build(List<JavaBeanModel> javaBeanModels,String outPutPath);
}
