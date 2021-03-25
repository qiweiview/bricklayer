package build;

import java.util.ArrayList;
import java.util.List;

public class BaseClassDescription {
    public static String test111="dddddd";

    private String belongPackage;
    private String shortName;
    private String fullName;
    private List<String> dependents=new ArrayList<>();


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
