package build.data_bases;

import java.util.ArrayList;
import java.util.List;

public class DBTableModel {
    private String name;

    private String originalName;

    private List<DBColumnModel> dbColumnModelList =new ArrayList();

    private String doPath;

    private String dtoPath;

    private String voPath;

    private String daoPath;

    private String primaryKey="id";


    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getDaoPath() {
        return daoPath;
    }

    public void setDaoPath(String daoPath) {
        this.daoPath = daoPath;
    }

    public String getDoPath() {
        return doPath;
    }

    public void setDoPath(String doPath) {
        this.doPath = doPath;
    }

    public String getDtoPath() {
        return dtoPath;
    }

    public void setDtoPath(String dtoPath) {
        this.dtoPath = dtoPath;
    }

    public String getVoPath() {
        return voPath;
    }

    public void setVoPath(String voPath) {
        this.voPath = voPath;
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
