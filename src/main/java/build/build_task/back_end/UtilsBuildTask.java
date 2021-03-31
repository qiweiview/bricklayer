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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilsBuildTask implements JavaBuildTask {
    private List<OutPutTask> outPutTasks = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();


    public UtilsBuildTask() {
        map.put("ResponseVo.java", "response_vo.ftl");
        map.put("DataNotFoundException.java", "data_not_found_exception.ftl");
    }

    @Override
    public List<OutPutTask> build(List<JavaBeanModel> javaBeanModels, String outPutPath) {
        JavaBeanModel x = javaBeanModels.get(0);
        map.forEach((beanName, templateName) -> {
            Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
            try {

                String relatively = StringUtils4V.javaPackagePath2SystemPath(  x.getContextModel().getUtilsBase())+File.separator+beanName;
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
