package build;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class BuildTest {
    public static void main(String[] args) throws IOException, TemplateException {
        Template template = FreemarkerTemplateBuilder.getTemplate("test.ftl");
        Map<String,String> m=new HashMap<>();
        m.put("hi","say hi");
        Writer out = new OutputStreamWriter(System.out);
        template.process(m,out);
    }
}
