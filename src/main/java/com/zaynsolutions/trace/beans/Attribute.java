package com.zaynsolutions.trace.beans;
import java.util.HashMap;
import java.util.Map;
public class Attribute {
    private String key;
    private Value value;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Value getValue() {
        return value;
    }
    public void setValue(Value value) {
        this.value = value;
    }
}