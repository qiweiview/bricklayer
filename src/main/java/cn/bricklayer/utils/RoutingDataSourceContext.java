package cn.bricklayer.utils;

public class RoutingDataSourceContext implements AutoCloseable {

    // holds data source key in thread local:
    static final ThreadLocal<String> threadLocalDataSourceKey = new ThreadLocal<>();

    public static String getDataSourceRoutingKey() {
        String key = threadLocalDataSourceKey.get();
        return key == null ? "masterDataSource" : key;
    }


    public static void setDataSourceRoutingKey(String dataSource) {
        threadLocalDataSourceKey.set(dataSource);
    }


    public void close() {
        threadLocalDataSourceKey.remove();
    }
}