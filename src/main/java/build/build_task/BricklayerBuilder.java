package build.build_task;

import build.build_task.back_end.*;
import build.build_task.output_task.OutPutTask;
import build.java_bean.JavaBeanModel;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BricklayerBuilder {
    private static List<JavaBuildTask> list = new ArrayList<>();

    static {
        list.add(new VOBuildTask());
        list.add(new DTOBuildTask());
        list.add(new DOBuildTask());
        list.add(new ControllerBuildTask());
        list.add(new ServiceIBuildTask());
        list.add(new ServiceImplBuildTask());
        list.add(new DaoBuildTask());
        list.add(new MapperBuildTask());
        list.add(new UtilsBuildTask());

    }

    public static void build(List<JavaBeanModel> javaBeanModels, String outPutPath) {
        List<OutPutTask> build = new ArrayList<>();
        list.forEach(x -> {
            build.addAll(x.build(javaBeanModels, outPutPath));

        });

        byte[] bytes = zipFiles(build);

        try {
            FileUtils.writeByteArrayToFile(new File("C:\\Users\\liuqiwei\\Desktop\\output.zip"),bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] zipFiles(List<OutPutTask> collect) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out = new ZipOutputStream(byteArrayOutputStream);
            for (OutPutTask file : collect) {
                String s = "back_end" + File.separator + file.getRelativelyPath();
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
