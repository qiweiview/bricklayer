package cn.buildSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class StructureConstant {


    public static List<String> templateScanPath = new ArrayList<>();
    public static List<InnerMethodDescription> methodTemplate = new ArrayList<>();


    static {
        methodTemplate.add(new InnerMethodDescription("save",false,true));
        methodTemplate.add(new InnerMethodDescription("update",false,true));
        methodTemplate.add(new InnerMethodDescription("remove",false,true));
        methodTemplate.add(new InnerMethodDescription("count",true,true));
        methodTemplate.add(new InnerMethodDescription("listAll",true,true));
        methodTemplate.add(new InnerMethodDescription("listPage",true,true));

        templateScanPath.add("back_end"+File.separator+"controller");
        templateScanPath.add("back_end"+File.separator+"serviceI");
        templateScanPath.add("back_end"+File.separator+"serviceI" + File.separator + "impl");
        templateScanPath.add("back_end"+File.separator+"dao");
        templateScanPath.add("back_end"+File.separator+"model" + File.separator + "domain_object");
        templateScanPath.add("back_end"+File.separator+"model" + File.separator + "transfer_object");
        templateScanPath.add("back_end"+File.separator+"model" + File.separator + "view_object");
        templateScanPath.add("back_end"+File.separator+"utils");
        templateScanPath.add("back_end"+File.separator+"mapper");

        templateScanPath.add("front_end");
    }


  public  static class InnerMethodDescription{
       private String name;
      private  boolean hasReturn;
      private boolean hasInput;

      public  InnerMethodDescription(String name, boolean hasReturn, boolean hasInput) {
          this.name = name;
          this.hasReturn = hasReturn;
          this.hasInput = hasInput;
      }

      public String getName() {
          return name;
      }

      public boolean hasReturn() {
          return hasReturn;
      }

      public boolean hasInput() {
          return hasInput;
      }
  }




}
