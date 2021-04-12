package com.buildSupport.build_task.back_end;

import com.buildSupport.build_task.JavaBuildTask;
import com.buildSupport.build_task.output_task.OutPutTask;
import com.buildSupport.java_bean.JavaBeanModel;
import com.buildSupport.utils.FreemarkerTemplateBuilder;
import com.buildSupport.utils.StringUtils4V;
import freemarker.template.Template;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ControllerBuildTask implements JavaBuildTask {


    private final String templateName = "controller.ftl";

    @Override
    public List<OutPutTask> build(List<JavaBeanModel> javaBeanModels) {
        List<OutPutTask> outPutTasks = new ArrayList<>();
        Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
        javaBeanModels.forEach(x -> {
            try {
                String relatively = StringUtils4V.javaPackagePath2SystemPath(  x.getContextModel().getControllerPath()) + ".java";
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, Charset.forName("utf-8"));
                template.process(x, outputStreamWriter);
                OutPutTask outPutTask = new OutPutTask(byteArrayOutputStream, relatively);
                outPutTasks.add(outPutTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return outPutTasks;
    }
}
