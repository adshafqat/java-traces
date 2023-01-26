package com.zaynsolutions.trace.beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Span {
    private String traceId;
    private String spanId;
    private String parentSpanId;
    private String name;
    private String kind;
    private String startTimeUnixNano;
    private String endTimeUnixNano;
    private List<Attribute> attributes = new ArrayList<Attribute>();
    private String droppedAttributesCount;
    private List<Event> events = new ArrayList<Event>();
    private String droppedEventsCount;
    private Status status;
    private List<Object> links = new ArrayList<Object>();
    private String droppedLinksCount;
    public String getTraceId() {
        return traceId;
    }
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
    public String getSpanId() {
        return spanId;
    }
    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }
    public String getParentSpanId() {
        return parentSpanId;
    }
    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getStartTimeUnixNano() {
        return startTimeUnixNano;
    }
    public void setStartTimeUnixNano(String startTimeUnixNano) {
        this.startTimeUnixNano = startTimeUnixNano;
    }
    public String getEndTimeUnixNano() {
        return endTimeUnixNano;
    }
    public void setEndTimeUnixNano(String endTimeUnixNano) {
        this.endTimeUnixNano = endTimeUnixNano;
    }
    public List<Attribute> getAttributes() {
        return attributes;
    }
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
    public String getDroppedAttributesCount() {
        return droppedAttributesCount;
    }
    public void setDroppedAttributesCount(String droppedAttributesCount) {
        this.droppedAttributesCount = droppedAttributesCount;
    }
    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    public String getDroppedEventsCount() {
        return droppedEventsCount;
    }
    public void setDroppedEventsCount(String droppedEventsCount) {
        this.droppedEventsCount = droppedEventsCount;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public List<Object> getLinks() {
        return links;
    }
    public void setLinks(List<Object> links) {
        this.links = links;
    }
    public String getDroppedLinksCount() {
        return droppedLinksCount;
    }
    public void setDroppedLinksCount(String droppedLinksCount) {
        this.droppedLinksCount = droppedLinksCount;
    }
} 