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
import java.util.ArrayList;
import java.util.List;

public class MapperBuildTask implements JavaBuildTask {

    private List<OutPutTask> outPutTasks = new ArrayList<>();
    private final String templateName = "mapper.ftl";

    @Override
    public List<OutPutTask> build(List<JavaBeanModel> javaBeanModels, String outPutPath) {
        Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
        javaBeanModels.forEach(x -> {
            try {
                String relatively = StringUtils4V.javaPackagePath2SystemPath(  x.getContextModel().getMapperPath()) + ".java";
                String absolutePath = outPutPath+File.separator+relatively;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                template.process(x, new OutputStreamWriter(byteArrayOutputStream));
                OutPutTask outPutTask = new OutPutTask(byteArrayOutputStream, relatively,absolutePath);
                outPutTasks.add(outPutTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return outPutTasks;
    }
}
