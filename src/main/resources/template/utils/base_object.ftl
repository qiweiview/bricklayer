${belongPackage}


import java.sql.Timestamp;

public class BaseObject {


private String createBy;

private String updateBy;

private Timestamp createTime;

private Timestamp updateTime;

public String getCreateBy() {
return createBy;
}

public void setCreateBy(String createBy) {
this.createBy = createBy;
}

public String getUpdateBy() {
return updateBy;
}

public void setUpdateBy(String updateBy) {
this.updateBy = updateBy;
}

public Timestamp getCreateTime() {
return createTime;
}

public void setCreateTime(Timestamp createTime) {
this.createTime = createTime;
}

public Timestamp getUpdateTime() {
return updateTime;
}

public void setUpdateTime(Timestamp updateTime) {
this.updateTime = updateTime;
}


}
