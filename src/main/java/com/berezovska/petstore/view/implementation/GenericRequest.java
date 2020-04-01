package com.berezovska.petstore.view.implementation;

import com.berezovska.petstore.controller.util.HttpHeaders;
import com.berezovska.petstore.controller.util.HttpVersion;
import com.berezovska.petstore.controller.util.RequestCommand;
import com.berezovska.petstore.controller.web.WebClient;
import com.berezovska.petstore.model.ApiResponse;
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
        headers.put("startline", startGenerate(RequestCommand.GET, path, HttpVersion.HTTP_1_1));
        return (T) webClient.GET(headers, clazz);
            }

    List<T> getListEntity(String path, Type clazz) {
        String startline = startGenerate(RequestCommand.GET, path, HttpVersion.HTTP_1_1);
        headers.put("startline", startline);
        return (List<T>) webClient.GETList(headers, clazz);

    }

    String getStringResult(String path){
        String startline = startGenerate(RequestCommand.GET, path, HttpVersion.HTTP_1_1);
        headers.put("startline", startline);
        try {
            return webClient.getResponseString(headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    String postEntity(T t){
        String startLine = startGenerate(RequestCommand.POST, t.getPath(), HttpVersion.HTTP_1_1);
        headers.put("startline", startLine);
        return webClient.POST(headers, t);
    }

    String postEntity(T[] t){
        String startLine = startGenerate(RequestCommand.POST, t[0].getPath() + "/createWithArray", HttpVersion.HTTP_1_1);
        headers.put("startline", startLine);
        return webClient.POST(headers, t);
    }

    String putEntity(T t){
        String startline = startGenerate(RequestCommand.PUT, t.getPath(), HttpVersion.HTTP_1_1);
        headers.put("startline", startline);
        return webClient.PUT(headers, t);
    }

    ApiResponse deleteEntity(String path, String apiKey){
        String startLine = startGenerate(RequestCommand.DELETE, path, HttpVersion.HTTP_1_1);
        headers.put("startline", startLine);
        return webClient.DELETE(headers, apiKey);
    }

    private String startGenerate(RequestCommand commands, String path, HttpVersion version){
        StringBuilder sb = new StringBuilder();
        sb.append(commands.name()).append(" ").append(path).append(" ").append(version.getName());
        return String.valueOf(sb);
    }
}

