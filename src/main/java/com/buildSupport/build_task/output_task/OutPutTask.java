package com.buildSupport.build_task.output_task;

import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.File;

@Data
public class OutPutTask {
    public static final String BACK_END="back_end";
    public static final String FRONT_END="front_end";

    private String belongPart=BACK_END;
    private ByteArrayOutputStream data;
    private String relativelyPath;

    public byte[] toByte(){
        return data.toByteArray();
    }

    public File toFile(){
        return new File(relativelyPath);
    }



    public OutPutTask(ByteArrayOutputStream data, String relativelyPath) {
        this.data = data;
        this.relativelyPath = relativelyPath;

    }

}
