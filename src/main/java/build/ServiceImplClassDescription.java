package build;

import java.util.ArrayList;
import java.util.List;

public class ServiceImplClassDescription extends BaseClassDescription {
    private String innerDao;
    private String interfaceName;
    private List<InnerMethodDescription> methods = new ArrayList<>();

    public void addOverWriteMethods(InnerMethodDescription innerMethodDescription){
        methods.add(innerMethodDescription);
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInnerDao() {
        return innerDao;
    }

    public void setInnerDao(String innerDao) {
        this.innerDao = innerDao;
    }

    public List<InnerMethodDescription> getMethods() {
        return methods;
    }

    public void setMethods(List<InnerMethodDescription> methods) {
        this.methods = methods;
    }
}
