package build_support.build_task;

import build_support.build_task.back_end.*;
import build_support.build_task.front_end.ListPageBuildTask;
import build_support.build_task.output_task.OutPutTask;
import build_support.java_bean.JavaBeanModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BricklayerBuilder {
    private static List<JavaBuildTask> list = new ArrayList<>();

    static {
        //back end
        list.add(new VOBuildTask());
        list.add(new DTOBuildTask());
        list.add(new DOBuildTask());
        list.add(new ControllerBuildTask());
        list.add(new ServiceIBuildTask());
        list.add(new ServiceImplBuildTask());
        list.add(new DaoBuildTask());
        list.add(new MapperBuildTask());
        list.add(new UtilsBuildTask());

        //front end
        list.add(new ListPageBuildTask());

    }

    public static  byte[] build(List<JavaBeanModel> javaBeanModels) {
        List<OutPutTask> build = new ArrayList<>();
        list.forEach(x -> {
            build.addAll(x.build(javaBeanModels));
        });

        byte[] bytes = zipFiles(build);

       return bytes;
    }

    public static byte[] zipFiles(List<OutPutTask> collect) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out = new ZipOutputStream(byteArrayOutputStream);
            for (OutPutTask file : collect) {
                String s = file.getBelongPart() + File.separator + file.getRelativelyPath();
                out.putNextEntry(new ZipEntry(s));
                out.write(file.toByte());
                out.closeEntry();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
