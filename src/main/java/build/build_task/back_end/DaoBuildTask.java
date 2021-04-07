package build.build_task.back_end;

import build.build_task.JavaBuildTask;
import build.build_task.output_task.OutPutTask;
import build.java_bean.JavaBeanModel;
import build.utils.FreemarkerTemplateBuilder;
import build.utils.StringUtils4V;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DaoBuildTask implements JavaBuildTask {


    private final String templateName = "dao.ftl";

    @Override
    public List<OutPutTask> build(List<JavaBeanModel> javaBeanModels) {
        List<OutPutTask> outPutTasks = new ArrayList<>();
        Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
        javaBeanModels.forEach(x -> {
            try {
                String relatively = StringUtils4V.javaPackagePath2SystemPath(  x.getContextModel().getDaoPath()) + ".java";
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
