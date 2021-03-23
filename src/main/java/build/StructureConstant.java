package build;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StructureConstant {
    private static List<String> baseDirect = new ArrayList<>();
    static {
        baseDirect.add("controller");
        baseDirect.add("serviceI");
        baseDirect.add("serviceI" + File.separator + "impl");
        baseDirect.add("dao");
        baseDirect.add("model" + File.separator + "domain_object");
        baseDirect.add("model" + File.separator + "transfer_object");
        baseDirect.add("model" + File.separator + "view_object");
    }


    public  static void  createDirects(String basePath){
        if (!basePath.endsWith(File.separator)){
            basePath+=File.separator;
        }
        String finalBasePath = basePath;
        baseDirect.forEach(x->{
            File file = new File(finalBasePath + x);
            if (!file.exists()){
                file.mkdirs();
            }
        });
    }


    public static List<String> getTemplateScanPaths(){
        return baseDirect;
    }


    public static void main(String[] args) {
//        createDirects("D:\\NewWorkSpace\\bricklayer\\src\\main\\java\\demo");

        Stream.of(1,2,3).filter(x->x>2).forEach(x->{
            System.out.println(x);
        });
    }
}
