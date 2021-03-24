package build.adapter;

import java.util.HashMap;
import java.util.Map;

public class MysqlTypeConvert implements TypeConvertI {
    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("VARCHAR", "java.lang.String");
        map.put("CHAR", "java.lang.String");
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
        map.put("DATETIME", "java.sql.Timestamp");
        map.put("TIMESTAMP", "java.sql.Timestamp");
        map.put("YEAR", "java.sql.Date");


    }

    @Override
    public String covert(String dbType) {
        String s = map.get(dbType.toUpperCase());
        if (s==null){
            throw new RuntimeException("can not found the java type for the "+dbType);
        }
        return s;
    }
}
