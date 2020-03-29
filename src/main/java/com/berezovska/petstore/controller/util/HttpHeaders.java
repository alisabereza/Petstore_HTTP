package com.berezovska.petstore.controller.util;

public enum HttpHeaders {
    HOST("Host"),
    CONNECTION("Connection"),
    ACCEPT("accept"),
    USER_AGENT("User-Agent"),
    REFERER("Referer"),
    ACCEPT_ENCODING("Accept-Encoding"),
    ACCEPT_LANGUAGE("Accept-Language"),
    CONTENT_TYPE("Content-Type"),
    API_KEY("api_key"),
    CONTENT_LENGTH("Content-Length");

    private String name;
    private String defaultValue;

    HttpHeaders(String initName) {
        name = initName;
    }

    public String getName() {
        return name;
    }
    public String getDefaultValue() {
        setDefValue();
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    private void setDefValue(){
        defaultValue =
                name.equals(HOST.name) ? "petstore.swagger.io" :
                        name.equals(CONNECTION.name) ? "keep-alive" :
                                name.equals(ACCEPT.name) ? "application/json" :
                                        name.equals(USER_AGENT.name) ? "" :
                                                name.equals(REFERER.name) ? "http://petstore.swagger.io" :
                                                        name.equals(ACCEPT_ENCODING.name) ? "gzip, deflate" :
                                                                name.equals(ACCEPT_LANGUAGE.name) ? "en-US,en;q=0.9" :
                                                                        name.equals(CONTENT_TYPE.name) ? "application/json" :
                                                                                null;
    }
}
