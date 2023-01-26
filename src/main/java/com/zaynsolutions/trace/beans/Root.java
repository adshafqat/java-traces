package com.zaynsolutions.trace.beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Root {
    private List<ResourceSpan> resourceSpans = new ArrayList<ResourceSpan>();
    public List<ResourceSpan> getResourceSpans() {
        return resourceSpans;
    }
    public void setResourceSpans(List<ResourceSpan> resourceSpans) {
        this.resourceSpans = resourceSpans;
    }
}