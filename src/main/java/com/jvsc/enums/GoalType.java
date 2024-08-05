package com.jvsc.enums;

public enum GoalType {
    DURACAO,
    CALORIAS;

    private int type;

    public int getType(){
        return type;
    }

    public void set(int type){
        this.type = type;
    }


}
