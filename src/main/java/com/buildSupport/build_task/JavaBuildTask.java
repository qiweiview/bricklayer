package com.buildSupport.build_task;

import com.buildSupport.build_task.output_task.OutPutTask;
import com.buildSupport.java_bean.JavaBeanModel;

import java.util.List;

public interface JavaBuildTask {



    public  List<OutPutTask> build(List<JavaBeanModel> javaBeanModels);
}
