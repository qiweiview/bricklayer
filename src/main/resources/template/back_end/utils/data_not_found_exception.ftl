package ${basePath};


public class DataNotFoundException extends RuntimeException{
public DataNotFoundException() {
super("未找到对应数据");
}
}
