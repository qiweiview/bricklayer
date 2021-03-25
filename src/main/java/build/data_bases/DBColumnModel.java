package build.data_bases;

public class DBColumnModel {
    private String name;

    private String originalName;

    private String type;

    private String comment;

    @Override
    public String toString() {
        return "DBColumnModel{" +
                "name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                '}';
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
