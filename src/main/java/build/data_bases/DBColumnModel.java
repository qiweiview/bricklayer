package build.data_bases;

public class DBColumnModel {
    private String name;

    private String originalName;

    private String type;

    private String comment;

    private String columnKey;

    private String extra;


    @Override
    public String toString() {
        return "DBColumnModel{" +
                "name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
