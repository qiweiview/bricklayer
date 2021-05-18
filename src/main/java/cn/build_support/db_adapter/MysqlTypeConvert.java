package cn.build_support.db_adapter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MysqlTypeConvert implements TypeConvertI {
    private static final Map<String, String> map = new HashMap<>();

    public static final Map<String, Object> default_value = new HashMap<>();


    static {
        Date date = new Date();
        long time = date.getTime();
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalTime localTime = LocalTime.now();
        Time time1 = Time.valueOf(localTime);
        default_value.put("java.lang.byte[]", new byte[0]);
        default_value.put("java.lang.Float", 666.66f);
        default_value.put("java.sql.Time", time1);
        default_value.put("java.lang.Double", 666.66d);
        default_value.put("java.sql.Timestamp", Timestamp.valueOf(localDateTime));
        default_value.put("java.time.LocalDateTime", format);
        default_value.put("java.lang.Integer", 666);
        default_value.put("java.sql.Date", new java.sql.Date(time));
        default_value.put("java.math.BigDecimal", BigDecimal.valueOf(666l));
        default_value.put("java.lang.Long", 666l);
        default_value.put("java.lang.Boolean", true);
        default_value.put("java.math.BigInteger", BigInteger.valueOf(666l));
        default_value.put("java.lang.String", "hello world");


        map.put("SET", "java.lang.String");
        map.put("ENUM", "java.lang.String");
        map.put("MEDIUMTEXT", "java.lang.String");
        map.put("MEDIUMBLOB", "java.lang.byte[]");

        map.put("VARCHAR", "java.lang.String");
        map.put("CHAR", "java.lang.String");
        map.put("LONGBLOB", "java.lang.byte[]");
        map.put("BLOB", "java.lang.byte[]");
        map.put("TEXT", "java.lang.String");
        map.put("INTEGER", "java.lang.Long");
        map.put("INT", "java.lang.Integer");
        map.put("TINYINT", "java.lang.Integer");
        map.put("SMALLINT", "java.lang.Integer");
        map.put("MEDIUMINT", "java.lang.Integer");
        map.put("BIT", "java.lang.Boolean");
        map.put("BIGINT", "java.math.BigInteger");
        map.put("FLOAT", "java.lang.Float");
        map.put("DOUBLE", "java.lang.Double");
        map.put("DECIMAL", "java.math.BigDecimal");
        map.put("BOOLEAN", "java.lang.Long");
        map.put("ID", "java.lang.Long");
        map.put("DATE", "java.sql.Date");
        map.put("TIME", "java.sql.Time");
        map.put("DATETIME", "java.time.LocalDateTime");
        map.put("TIMESTAMP", "java.sql.Timestamp");
        map.put("YEAR", "java.sql.Date");


    }

    public static Object getDefaultValue(String typeString) {
        Object o = default_value.get(typeString);
        if (o == null) {
            o = "";
        }
        return o;

    }

    @Override
    public String covert(String dbType) {
        String s = map.get(dbType.toUpperCase());
        if (s == null) {
            throw new RuntimeException("can not found the java type for the " + dbType);
        }
        return s;
    }

    @Override
    public boolean checkPrimary(String extra) {
        return "PRI".equals(extra);
    }

    @Override
    public boolean checkAutoIncrease(String columnKey) {
        return "auto_increment".equals(columnKey);
    }
}
