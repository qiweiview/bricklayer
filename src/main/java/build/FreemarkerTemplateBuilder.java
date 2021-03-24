package build;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FreemarkerTemplateBuilder {
    private static String path;
    private static Configuration cfg;
    private static Map<String, Template> templateMap = new ConcurrentHashMap<>();

    static {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL template = classloader.getResource("template");
        path = template.getFile();




        cfg = new Configuration(Configuration.VERSION_2_3_29);
        TemplateLoader[] templateLoaders = StructureConstant.templateScanPath.stream().map(x -> {
            try {
                FileTemplateLoader ftl1 = new FileTemplateLoader(new File(path + File.separator+x));
                return ftl1;
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("crate template fail");
            }
        }).toArray(TemplateLoader[]::new);

        MultiTemplateLoader mtl = new MultiTemplateLoader(templateLoaders);
        cfg.setTemplateLoader(mtl);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
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
}
