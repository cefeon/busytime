package com.cefeon.busytime;

import com.google.gson.Gson;

public class JsonError {
    private String message;
    private int status = 404;

    public JsonError(int status, String message){
        this.status = status;
        this.message = message;
    }

    public String toGson(){
        Gson g = new Gson();
        return g.toJson(this);
    }


}
