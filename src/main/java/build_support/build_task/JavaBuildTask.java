package build_support.build_task;

import build_support.build_task.output_task.OutPutTask;
import build_support.java_bean.JavaBeanModel;

import java.util.List;

public interface JavaBuildTask {



    public  List<OutPutTask> build(List<JavaBeanModel> javaBeanModels);
}
