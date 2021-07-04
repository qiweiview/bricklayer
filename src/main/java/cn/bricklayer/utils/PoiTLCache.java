package cn.bricklayer.utils;


import cn.build_support.db_adapter.MysqlTypeConvert;
import cn.build_support.java_bean.JavaBeanModel;
import cn.build_support.java_bean.JavaFiledModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 废弃
 */
@Deprecated
public class PoiTLCache {
    private static XWPFTemplate compile;

    static {
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder().bind("fieldList", policy).build();
        InputStream resourceAsStream = PoiTLCache.class.getClassLoader().getResourceAsStream("template/document_template.docx");
        compile = XWPFTemplate.compile(resourceAsStream, config);
    }


    public static byte[] getDocumentFile(JavaBeanModel javaBeanModel) {

        /* ------------生成渲染数据模型------------ */
        Map<String, Object> map = new HashMap<>();
        StringBuilder path = new StringBuilder();
        String contextPath = javaBeanModel.getContextPath();
        if (contextPath != null && !"".equals(contextPath)) {
            path.append("/" + contextPath);
        }
        String className = javaBeanModel.getClassName();
        path.append("/" + StringUtils4V.lowercaseFirstLetter(className));
        map.put("path", path);
        List<JavaFiledModel> fieldList = javaBeanModel.getFieldList();
        map.put("fieldList", fieldList);
        Map<String, Object> jsonMap = new HashMap<>();
        fieldList.forEach(x -> {
            jsonMap.put(x.getBeanName(), MysqlTypeConvert.getDefaultValue(x.getJavaType()));
        });
        map.put("request_json", JacksonUtils.jsonFormat(JacksonUtils.obj2Str(jsonMap)));
        map.put("response_json", JacksonUtils.jsonFormat(JacksonUtils.obj2Str(ResponseVo.success(jsonMap))));

        Page page = new Page(1, 5, 2);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(jsonMap);
        list.add(jsonMap);
        page.setRecords(list);
        map.put("response_page_json", JacksonUtils.jsonFormat(JacksonUtils.obj2Str(ResponseVo.success(page))));
        /* ------------生成渲染数据模型------------ */


        XWPFTemplate render = compile.render(map);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            render.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
