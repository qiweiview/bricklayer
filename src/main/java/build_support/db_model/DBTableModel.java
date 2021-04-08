package build_support.db_model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象表模型
 */
@Data
public class DBTableModel {

    private String originalTableName;//原表名

    private List<DBColumnModel> dbColumnModelList =new ArrayList();


}
