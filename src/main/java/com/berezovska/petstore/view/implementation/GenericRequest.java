package com.berezovska.petstore.view.implementation;

import com.berezovska.petstore.controller.util.HttpHeaders;
import com.berezovska.petstore.controller.util.HttpVersion;
import com.berezovska.petstore.controller.util.RequestCommands;
import com.berezovska.petstore.controller.web.WebClient;
import com.berezovska.petstore.model.Entity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GenericRequest<T extends Entity> {
    static WebClient webClient;

    static{
        try {
            webClient = new WebClient(HttpHeaders.HOST.getDefaultValue(), 80);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Map<String, String> headers = new HashMap<>();

    GenericRequest(){
        headers.put(HttpHeaders.HOST.getName(), HttpHeaders.HOST.getDefaultValue());
        headers.put(HttpHeaders.ACCEPT.getName(), HttpHeaders.ACCEPT.getDefaultValue());
        headers.put(HttpHeaders.ACCEPT_LANGUAGE.getName(), HttpHeaders.ACCEPT_LANGUAGE.getDefaultValue());
        headers.put(HttpHeaders.ACCEPT_ENCODING.getName(), HttpHeaders.ACCEPT_ENCODING.getDefaultValue());
        headers.put(HttpHeaders.REFERER.getName(), HttpHeaders.REFERER.getDefaultValue());
        headers.put(HttpHeaders.CONNECTION.getName(), HttpHeaders.CONNECTION.getDefaultValue());
    }

    T getEntityByPath(String path, Class clazz){
        StringBuilder sb = new StringBuilder();
        headers.put("startline", startGenerate(RequestCommands.GET, path, HttpVersion.HTTP_1_1));
        T t = (T) webClient.GET(headers, clazz);
        return t;
    }

    List<T> getListEntity(String path, Type clazz){
        String startline = startGenerate(RequestCommands.GET, path, HttpVersion.HTTP_1_1);
        headers.put("startline", startline);
        List<T> ts = (List<T>) webClient.GETList(headers, clazz);
        return ts;
    }

    String getStringResult(String path){
        String startline = startGenerate(RequestCommands.GET, path, HttpVersion.HTTP_1_1);
        headers.put("startline", startline);
        try {
            return webClient.getResponceString(headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    String postEntity(T t){
        String startLine = startGenerate(RequestCommands.POST, t.getPath(), HttpVersion.HTTP_1_1);
        headers.put("startline", startLine);
        return webClient.POST(headers, t);
    }

    String postEntity(T[] t){
        String startLine = startGenerate(RequestCommands.POST, t[0].getPath() + "/createWithArray", HttpVersion.HTTP_1_1);
        headers.put("startline", startLine);
        return webClient.POST(headers, t);
    }

    String putEntity(T t){
        String startline = startGenerate(RequestCommands.PUT, t.getPath(), HttpVersion.HTTP_1_1);
        headers.put("startline", startline);
        return webClient.PUT(headers, t);
    }

    String deleteEntity(String path, String apiKey){
        String startLine = startGenerate(RequestCommands.DELETE, path, HttpVersion.HTTP_1_1);
        headers.put("startline", startLine);
        return webClient.DELETE(headers, apiKey);
    }

    private String startGenerate(RequestCommands commands, String path, HttpVersion version){
        StringBuilder sb = new StringBuilder();
        sb.append(commands.name()).append(" ").append(path).append(" ").append(version.getName());
        return String.valueOf(sb);
    }
}

