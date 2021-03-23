package build;

import java.util.ArrayList;
import java.util.List;

public class DaoDescription extends BaseDescription {
    private List<InnerMethodDescription> methods = new ArrayList<>();

    public void addInterfaceMethods(InnerMethodDescription innerMethodDescription){
        methods.add(innerMethodDescription);
    }

    public List<InnerMethodDescription> getMethods() {
        return methods;
    }

    public void setMethods(List<InnerMethodDescription> methods) {
        this.methods = methods;
    }
}
