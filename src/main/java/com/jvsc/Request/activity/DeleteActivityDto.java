package com.jvsc.Request.activity;

public class DeleteActivityDto {
    private long id;
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    

    public DeleteActivityDto() {
    }

    public DeleteActivityDto(long id) {
        this.id = id;
        
    }
}
