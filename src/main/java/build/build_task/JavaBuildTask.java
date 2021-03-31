package build.build_task;

import build.build_task.output_task.OutPutTask;
import build.java_bean.JavaBeanModel;

import java.util.ArrayList;
import java.util.List;

public interface JavaBuildTask {



    public  List<OutPutTask> build(List<JavaBeanModel> javaBeanModels, String outPutPath);
}
