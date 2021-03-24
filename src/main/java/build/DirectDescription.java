package build;

import java.util.ArrayList;
import java.util.List;

public class DirectDescription {
    private String path;

    private String absolutePath;

    private List<BaseClassDescription> list=new ArrayList<>();

    public DirectDescription(String path, String absolutePath, List<BaseClassDescription> list) {
        this.path = path;
        this.absolutePath = absolutePath;
        this.list = list;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public List<BaseClassDescription> getList() {
        return list;
    }

    public void setList(List<BaseClassDescription> list) {
        this.list = list;
    }
}
