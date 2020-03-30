package com.berezovska.petstore.controller.util;

import java.util.Arrays;
import java.util.Optional;

public enum RequestCommand {
    GET("get"), POST("post"), PUT("put"), DELETE("delete");

    private String command;

    RequestCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Optional<RequestCommand> getCommand(String command) {
        return Arrays.stream(RequestCommand.values())
                .filter(enumValue -> enumValue.getCommand().equals(command))
                .findAny();
    }
}
