package build;

import java.util.ArrayList;
import java.util.List;

public class ObjectClassDescription extends BaseClassDescription {
    private List<InnerAttributeDescription> attributes = new ArrayList<>();

    public void addInnerAttributeDescription(InnerAttributeDescription innerAttributeDescription) {
        attributes.add(innerAttributeDescription);
    }

    public List<InnerAttributeDescription> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<InnerAttributeDescription> attributes) {
        this.attributes = attributes;
    }
}
