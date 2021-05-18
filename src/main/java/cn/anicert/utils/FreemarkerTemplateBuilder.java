package cn.anicert.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FreemarkerTemplateBuilder {
    private static Map<String, Template> templateMap = new ConcurrentHashMap<>();

    static {

    }

    public static Template getTemplateByString(String templateString) {
        String fixName = "fixName";
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate(fixName, templateString);
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setTemplateLoader(stringTemplateLoader);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        try {
            Template template = cfg.getTemplate(fixName);
            return template;
        } catch (IOException e) {
            throw new MessageRuntimeException(e.getMessage());
        }
    }

    public static Configuration getConfigurationByTemplateMap(Map<String,String> map) {

        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        map.forEach((k,v)->{
            stringTemplateLoader.putTemplate(k, v);
        });

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setTemplateLoader(stringTemplateLoader);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
       return cfg;
    }



//    public static void main(String[] args) throws IOException, TemplateException {
//
//        Template template = getTemplate("tt");
//        Map<String,String> map=new HashMap<>();
//        map.put("name","view");
//        template.process(map,new OutputStreamWriter(System.out));
//    }
}
