package com.cefeon.busytime;

import com.google.gson.Gson;

public class JsonError {
    private String message;
    private int status;

    public JsonError(int status, String message){
        this.status = status;
        this.message = message;
    }

    public String createJSON(){
        Gson g = new Gson();
        return g.toJson(this);
    }


}
