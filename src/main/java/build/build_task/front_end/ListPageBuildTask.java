package build.build_task.front_end;

import build.build_task.JavaBuildTask;
import build.build_task.output_task.OutPutTask;
import build.java_bean.JavaBeanModel;
import build.utils.FreemarkerTemplateBuilder;
import build.utils.StringUtils4V;
import freemarker.template.Template;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ListPageBuildTask implements JavaBuildTask {

    private final String templateName = "list_page.ftl";

    @Override
    public List<OutPutTask> build(List<JavaBeanModel> javaBeanModels) {
        List<OutPutTask> outPutTasks = new ArrayList<>();
        Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
        javaBeanModels.forEach(x -> {
            try {
                String relatively = StringUtils4V.javaPackagePath2SystemPath(x.getClassName()) + "List.vue";
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, Charset.forName("utf-8"));
                template.process(x, outputStreamWriter);
                OutPutTask outPutTask = new OutPutTask(byteArrayOutputStream, relatively);
                outPutTask.setBelongPart(OutPutTask.FRONT_END);
                outPutTasks.add(outPutTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return outPutTasks;
    }
}
