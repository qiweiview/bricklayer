package build_support.db_adapter;

public class TypeConverter {
    private static TypeConvertI typeConvertI;

    static{
        typeConvertI=new MysqlTypeConvert();
    }

    public static String covert(String dbType){
        return typeConvertI.covert(dbType);
    }

    public static boolean checkPrimary(String extra) {
        return typeConvertI.checkPrimary(extra);
    }

    public static boolean checkAutoIncrease(String columnKey) {
        return typeConvertI.checkAutoIncrease(columnKey);
    }
}
