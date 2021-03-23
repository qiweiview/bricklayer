package build;

import java.util.ArrayList;
import java.util.List;

public class BaseDescription {
    private String belongPackage;
    private String className;
    private List<String> dependents=new ArrayList<>();

    public void addDependent(String dependent){
        dependents.add(dependent);
    }

    public List<String> getDependents() {
        return dependents;
    }

    public void setDependents(List<String> dependents) {
        this.dependents = dependents;
    }

    public String getBelongPackage() {
        return belongPackage;
    }

    public void setBelongPackage(String belongPackage) {
        this.belongPackage = belongPackage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
