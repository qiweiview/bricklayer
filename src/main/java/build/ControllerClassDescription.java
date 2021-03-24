package build;

import java.util.ArrayList;
import java.util.List;

public class ControllerClassDescription extends BaseClassDescription {

    private String baseMapping;
    private String innerService;
    private List<InnerMethodDescription> methods = new ArrayList<>();

    public void addMappingMethod(InnerMethodDescription innerMethodDescription){
        methods.add(innerMethodDescription);
    }

    public List<InnerMethodDescription> getMethods() {
        return methods;
    }

    public void setMethods(List<InnerMethodDescription> methods) {
        this.methods = methods;
    }



    public String getBaseMapping() {
        return baseMapping;
    }

    public void setBaseMapping(String baseMapping) {
        this.baseMapping = baseMapping;
    }



    public String getInnerService() {
        return innerService;
    }

    public void setInnerService(String innerService) {
        this.innerService = innerService;
    }
}
