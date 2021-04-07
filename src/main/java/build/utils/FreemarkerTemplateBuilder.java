package build.utils;

import build.StructureConstant;
import build.utils.SuffixManager;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FreemarkerTemplateBuilder {
    public static SuffixManager suffixManager = new SuffixManager();
    private static String path;
    private static Configuration cfg;
    private static Map<String, Template> templateMap = new ConcurrentHashMap<>();

    static {
        //加载项项目目录下文件
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL template = classloader.getResource("template");
        path = template.getFile();


        cfg = new Configuration(Configuration.VERSION_2_3_29);

        //循环加载模板
        TemplateLoader[] templateLoaders = StructureConstant.templateScanPath.stream().map(x -> {
            try {
                FileTemplateLoader ftl1 = new FileTemplateLoader(new File(path + File.separator + x));
                return ftl1;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("crate template fail");
            }
        }).toArray(TemplateLoader[]::new);

        MultiTemplateLoader mtl = new MultiTemplateLoader(templateLoaders);

        StringTemplateLoader stringTemplateLoader=new StringTemplateLoader();
        stringTemplateLoader.putTemplate("tt","hi ${name}");
        cfg.setTemplateLoader(stringTemplateLoader);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        try {
            cfg.setSharedVariable("suffixManager", suffixManager);
        } catch (TemplateModelException e) {
            throw new RuntimeException("set Shared Variable fail");
        }
    }


    public static Template getTemplate(String templateName) {
        try {
            Template template = templateMap.get(templateName);
            if (template == null) {
                template = cfg.getTemplate(templateName);
                templateMap.put(templateName, template);
            }
            return template;
        } catch (IOException e) {
            throw new RuntimeException("get template fail cause: " + e);
        }
    }

    public static void main(String[] args) throws IOException, TemplateException {

        Template template = getTemplate("tt");
        Map<String,String> map=new HashMap<>();
        map.put("name","view");
        template.process(map,new OutputStreamWriter(System.out));
    }
}
