package build.build_task.output_task;

import lombok.Data;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Data
public class OutPutTask {
    private ByteArrayOutputStream data;
    private String relativelyPath;
    private String absolutePath;

    public byte[] toByte(){
        return data.toByteArray();
    }

    public File toFile(){
        return new File(relativelyPath);
    }

    public void writeToDisk(){
        try {
            FileUtils.writeByteArrayToFile(new File(absolutePath),data.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (data!=null){
                try {
                    data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public OutPutTask(ByteArrayOutputStream data, String relativelyPath, String absolutePath) {
        this.data = data;
        this.relativelyPath = relativelyPath;
        this.absolutePath = absolutePath;
    }

}
