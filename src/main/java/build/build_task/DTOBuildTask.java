package build.build_task;

import build.java_bean.JavaBeanModel;
import build.utils.FreemarkerTemplateBuilder;
import build.utils.StringUtils4V;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class DTOBuildTask implements JavaBuildTask {


    private final String templateName ="data_transfer_object.ftl";

    @Override
    public void build(List<JavaBeanModel> javaBeanModels,String outPutPath) {
        Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
        javaBeanModels.forEach(x->{
            FileOutputStream fileOutputStream = null;
            try {
                String path=StringUtils4V.javaPackagePath2SystemPath(outPutPath + File.separator + x.getContextModel().getDtoPath())+".java";
                fileOutputStream = FileUtils.openOutputStream(new File(path));
                template.process(x, new OutputStreamWriter(fileOutputStream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
