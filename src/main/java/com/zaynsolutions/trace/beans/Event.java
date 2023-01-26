package com.zaynsolutions.trace.beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Event {
    private List<Attribute> attributes = new ArrayList<Attribute>();
    private String name;
    private String timeUnixNano;
    private String droppedAttributesCount;
    public List<Attribute> getAttributes() {
        return attributes;
    }
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTimeUnixNano() {
        return timeUnixNano;
    }
    public void setTimeUnixNano(String timeUnixNano) {
        this.timeUnixNano = timeUnixNano;
    }
    public String getDroppedAttributesCount() {
        return droppedAttributesCount;
    }
    public void setDroppedAttributesCount(String droppedAttributesCount) {
        this.droppedAttributesCount = droppedAttributesCount;
    }
}