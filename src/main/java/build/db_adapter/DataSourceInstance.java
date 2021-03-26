package build.db_adapter;

import build.db_model.DBTableModel;

import java.util.List;

public interface DataSourceInstance {
    public List<DBTableModel> getDBTableModels();
}
