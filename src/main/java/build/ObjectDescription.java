package build;

import java.util.ArrayList;
import java.util.List;

public class ObjectDescription extends BaseDescription {
    private List<InnerAttributeDescription> attributes = new ArrayList<>();

    public void addInnerAttributeDescription(InnerAttributeDescription innerAttributeDescription) {
        attributes.add(innerAttributeDescription);
    }
}
