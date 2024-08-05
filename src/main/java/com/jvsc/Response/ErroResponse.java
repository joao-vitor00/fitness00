package com.jvsc.Response;

import java.time.LocalDateTime;

public class ErroResponse {
    private String message;
    private int status;
    private String path;
    private String timestamp;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

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