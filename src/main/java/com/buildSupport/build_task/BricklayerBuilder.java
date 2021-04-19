package com.buildSupport.build_task;

import com.buildSupport.build_task.back_end.*;
import com.buildSupport.build_task.front_end.ListPageBuildTask;
import com.buildSupport.build_task.output_task.OutPutTask;
import com.buildSupport.java_bean.JavaBeanModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BricklayerBuilder {
    private static List<JavaBuildTask> taskList = new ArrayList<>();

    static {
        //back end
        taskList.add(new VOBuildTask());
        taskList.add(new DTOBuildTask());
        taskList.add(new DOBuildTask());
        taskList.add(new ControllerBuildTask());
        taskList.add(new ServiceIBuildTask());
        taskList.add(new ServiceImplBuildTask());
        taskList.add(new DaoBuildTask());
        taskList.add(new MapperBuildTask());
        taskList.add(new UtilsBuildTask());

        //front end
        taskList.add(new ListPageBuildTask());

    }

    public static  byte[] build(List<JavaBeanModel> javaBeanModels) {
        List<OutPutTask> build = new ArrayList<>();
        taskList.forEach(x -> {
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
