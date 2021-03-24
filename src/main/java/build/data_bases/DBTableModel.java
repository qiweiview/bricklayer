package build.data_bases;

import java.util.ArrayList;
import java.util.List;

public class DBTableModel {
    private String name;

    private List<DBColumnModel> dbColumnModelList =new ArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DBColumnModel> getDbColumnModelList() {
        return dbColumnModelList;
    }

    public void setDbColumnModelList(List<DBColumnModel> dbColumnModelList) {
        this.dbColumnModelList = dbColumnModelList;
    }
}
