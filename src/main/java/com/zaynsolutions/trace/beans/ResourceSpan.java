package com.zaynsolutions.trace.beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ResourceSpan {
    private Resource resource;
    private List<ScopeSpan> scopeSpans = new ArrayList<ScopeSpan>();
    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public List<ScopeSpan> getScopeSpans() {
        return scopeSpans;
    }
    public void setScopeSpans(List<ScopeSpan> scopeSpans) {
        this.scopeSpans = scopeSpans;
    }
}