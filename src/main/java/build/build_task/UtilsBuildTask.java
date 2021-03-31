package build.build_task;

import build.java_bean.JavaBeanModel;
import build.utils.FreemarkerTemplateBuilder;
import build.utils.StringUtils4V;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilsBuildTask implements JavaBuildTask {

    private Map<String,String> map=new HashMap<>();



    public UtilsBuildTask() {
        map.put("ResponseVo.java","response_vo.ftl");
        map.put("DataNotFoundException.java","data_not_found_exception.ftl");
    }

    @Override
    public void build(List<JavaBeanModel> javaBeanModels,String outPutPath) {
        JavaBeanModel x = javaBeanModels.get(0);
        map.forEach((beanName,templateName)->{
            Template template = FreemarkerTemplateBuilder.getTemplate(templateName);
            FileOutputStream fileOutputStream = null;
            try {
                String s = outPutPath + File.separator + x.getContextModel().getUtilsBase();

                String path=StringUtils4V.javaPackagePath2SystemPath(s)+File.separator+beanName;
                fileOutputStream = FileUtils.openOutputStream(new File(path));
                template.process(x, new OutputStreamWriter(fileOutputStream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
}
