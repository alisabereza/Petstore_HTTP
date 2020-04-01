package com.berezovska.petstore.controller.web;

import com.berezovska.petstore.controller.util.HttpHeaders;
import com.berezovska.petstore.model.ApiResponse;
import com.berezovska.petstore.model.Entity;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.List;
import java.util.Map;

public class WebClient<T extends Entity> implements Request<T> {
    private static Socket socket;
    private String host;
    private InetAddress ipHost;
    private int port;
    private HttpHeaders httpHeaders;
    private Map<String, String> headers;


    public WebClient(String hostname, int port) throws IOException {
        initSocket(hostname, port);
    }

    private void initSocket(String hostname, int port) throws IOException {
        this.host = hostname;
        this.ipHost = InetAddress.getByName(hostname);
        socket = new Socket(ipHost, port);
    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public T GET(Map<String, String> headers, Class<T> clazz) {

        try {
            this.headers = headers;
            String toJson = getResponseResult();

            String json = toJson.substring(toJson.indexOf("\r\n\r\n"));
            System.out.println(json);
            return new Gson().fromJson(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<T> GETList(Map<String, String> headers, Type clazz) {
        try {
            this.headers = headers;
            String toJson = getResponseResult();
            System.out.println(toJson);
            String json = toJson.substring(toJson.indexOf("\r\n\r\n") + 3);
            return new Gson().fromJson(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String POST(Map<String, String> headers, T entity) {
        this.headers = headers;
        String body = new Gson().toJson(entity);
        return post(body);
    }

    public String POST(Map<String, String> headers, T[] entity) {
        this.headers = headers;
        String body = new Gson().toJson(entity);
        return post(body);
    }

    private String post(String body) {
        headers.put("body", body);
        headers.put(HttpHeaders.CONTENT_LENGTH.getName(), String.valueOf(body.getBytes().length));
        headers.put(HttpHeaders.CONTENT_TYPE.getName(), HttpHeaders.CONTENT_TYPE.getDefaultValue());
        try {
            String result = getResponseResult();
            headers.remove(HttpHeaders.CONTENT_LENGTH.getName());
            headers.remove(HttpHeaders.CONTENT_TYPE.getName());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String PUT(Map<String, String> headers, T t) {
        return POST(headers, t);
    }

    @Override
    public ApiResponse DELETE(Map<String, String> headers, String apiKey) {

        try {
            this.headers = headers;
            headers.put(HttpHeaders.API_KEY.getName(), apiKey);
            String result = getResponseResult();
            headers.remove(HttpHeaders.API_KEY.getName());
            String json = result.substring(result.indexOf("\r\n\r\n"));
            return new Gson().fromJson(json, ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getResponseString(Map<String, String> headers) throws IOException {
        this.headers = headers;
        return getResponseResult();
    }

    private String getResponseResult() throws IOException {

        initSocket(HttpHeaders.HOST.getDefaultValue(), 80);
        BufferedWriter writer;
        writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(headers.get("startline"));

        String body = null;
        if (headers.containsKey("body")) {
            body = headers.remove("body");
        }

        writer.write(headers.remove("startline") + "\r\n");
        for (Map.Entry<String, String> e :
                headers.entrySet()) {
            System.out.print(e.getKey() + ": " + e.getValue() + "\r\n");
            writer.write(e.getKey() + ": " + e.getValue() + "\r\n");
        }
        writer.write("\r\n");
        if (null != body) {
            writer.write(body + "\r\n");
        }
        System.out.println((null == body) ? "" : "\n" + body);
        writer.flush();


        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\r\n");
        }
        String result = String.valueOf(sb);
        writer.close();
        socket.close();

        return result;
    }
}
