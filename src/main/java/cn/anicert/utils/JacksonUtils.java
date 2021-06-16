package cn.anicert.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();


    public static String jsonFormat(String json) {
        return json.replaceAll("\\{", "{\n    ")
                .replaceAll("}", "\n}")
                .replaceAll(",", ",\n    ");
    }


    public static <T> T str2obj(String jsonStr, Class<T> tClass) {
        try {
            return objectMapper.readValue(jsonStr, tClass);
        } catch (JsonProcessingException e) {
            throw new MessageRuntimeException("fail cause:" + e.getMessage());
        }

    }

    public static String obj2Str(Object o) {
        if (null == o) {
            return "";
        }
        try {
            String s = objectMapper.writeValueAsString(o);
            return s;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
