package build_support.build_task.back_end;

import build_support.build_task.JavaBuildTask;
import build_support.build_task.output_task.OutPutTask;
import build_support.java_bean.JavaBeanModel;
import build_support.utils.FreemarkerTemplateBuilder;
import build_support.utils.StringUtils4V;
import freemarker.template.Template;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DOBuildTask implements JavaBuildTask {


    private final String templateName = "domain_object.ftl";

    @Override
    public List<OutPutTask> build(List<JavaBeanModel> javaBeanModels) {
        List<OutPutTask> outPutTasks = new ArrayList<>();
        Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
        javaBeanModels.forEach(x -> {
            try {
                String relatively = StringUtils4V.javaPackagePath2SystemPath(  x.getContextModel().getDoPath()) + ".java";
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
