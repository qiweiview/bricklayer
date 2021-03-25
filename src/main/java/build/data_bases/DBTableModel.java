package build.data_bases;

import java.util.ArrayList;
import java.util.List;

public class DBTableModel {
    private String name;

    private String originalName;

    private List<DBColumnModel> dbColumnModelList =new ArrayList();

    @Override
    public String toString() {
        return "DBTableModel{" +
                "name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", dbColumnModelList=" + dbColumnModelList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public List<DBColumnModel> getDbColumnModelList() {
        return dbColumnModelList;
    }

    public void setDbColumnModelList(List<DBColumnModel> dbColumnModelList) {
        this.dbColumnModelList = dbColumnModelList;
    }
}
