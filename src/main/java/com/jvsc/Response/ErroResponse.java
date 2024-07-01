package com.jvsc.Response;

import java.time.LocalDateTime;

public class ErroResponse {
    private String message;
    private int status;
    private String path;
    private String timestamp;

    public ErroResponse(String message, int status){
        this.message = message;
        this.status = status;
        this.path = "";
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErroResponse(String message, int status, String path){
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = LocalDateTime.now().toString();
    }
}