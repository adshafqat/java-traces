package com.zaynsolutions.trace.beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Resource {
    private List<Attribute> attributes = new ArrayList<Attribute>();
    private Integer droppedAttributesCount;
    public List<Attribute> getAttributes() {
        return attributes;
    }
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
    public Integer getDroppedAttributesCount() {
        return droppedAttributesCount;
    }
    public void setDroppedAttributesCount(Integer droppedAttributesCount) {
        this.droppedAttributesCount = droppedAttributesCount;
    }
}
