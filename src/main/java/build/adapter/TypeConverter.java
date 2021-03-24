package build.adapter;

public class TypeConverter {
    private static TypeConvertI typeConvertI;

    static{
        typeConvertI=new MysqlTypeConvert();
    }

    public static String covert(String dbType){
        return typeConvertI.covert(dbType);
    }
}
