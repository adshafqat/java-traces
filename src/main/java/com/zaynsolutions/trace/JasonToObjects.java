package com.zaynsolutions.trace;
import com.zaynsolutions.trace.beans.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
public class JasonToObjects {
    
    public Root jsonToJava(String jsonData){
    com.zaynsolutions.trace.beans.Root resourceSpaRoot=null;
    try {
        ObjectMapper mapper = new ObjectMapper();
        resourceSpaRoot = mapper.readValue(jsonData, Root.class);
        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resourceSpaRoot);
        System.out.println(data);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    return resourceSpaRoot;  
    }

}
