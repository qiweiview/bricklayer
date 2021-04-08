package build_support.db_adapter;

public interface TypeConvertI {
    public String covert(String dbType);

    boolean checkPrimary(String extra);

    boolean checkAutoIncrease(String columnKey);
}
