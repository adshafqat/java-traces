package com.zaynsolutions.trace.beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ScopeSpan {
    private Scope scope;
    private List<Span> spans = new ArrayList<Span>();
    public Scope getScope() {
        return scope;
    }
    public void setScope(Scope scope) {
        this.scope = scope;
    }
    public List<Span> getSpans() {
        return spans;
    }
    public void setSpans(List<Span> spans) {
        this.spans = spans;
    }
}