package com.jvsc.Request.user;

public class DeleteUserDto {

    private long id;
    

    public DeleteUserDto() {
    }

    public DeleteUserDto(long id) {
        this.id = id;
        
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    

}
