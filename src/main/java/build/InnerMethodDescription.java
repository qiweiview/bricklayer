package build;

public class InnerMethodDescription {
    private String path;
    private String  name;
    private String description;
    private boolean hasReturn=false;


    public InnerMethodDescription() {
    }

    public InnerMethodDescription(String description) {
        this.description = description;
    }

    public InnerMethodDescription(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasReturn() {
        return hasReturn;
    }

    public void setHasReturn(boolean hasReturn) {
        this.hasReturn = hasReturn;
    }
}
