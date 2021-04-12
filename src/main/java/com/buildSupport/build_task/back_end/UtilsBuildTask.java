package com.buildSupport.build_task.back_end;

import com.buildSupport.build_task.JavaBuildTask;
import com.buildSupport.build_task.output_task.OutPutTask;
import com.buildSupport.java_bean.JavaBeanModel;
import com.buildSupport.utils.FreemarkerTemplateBuilder;
import com.buildSupport.utils.StringUtils4V;
import freemarker.template.Template;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilsBuildTask implements JavaBuildTask {

    private Map<String, String> map = new HashMap<>();


    public UtilsBuildTask() {
        map.put("ResponseVo.java", "response_vo.ftl");
        map.put("DataNotFoundException.java", "data_not_found_exception.ftl");
    }

    @Override
    public List<OutPutTask> build(List<JavaBeanModel> javaBeanModels ) {
        List<OutPutTask> outPutTasks = new ArrayList<>();
        JavaBeanModel x = javaBeanModels.get(0);
        map.forEach((beanName, templateName) -> {
            Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
            try {

                String relatively = StringUtils4V.javaPackagePath2SystemPath(  x.getContextModel().getUtilsBase())+File.separator+beanName;
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
