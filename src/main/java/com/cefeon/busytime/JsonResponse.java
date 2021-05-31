package com.cefeon.busytime;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonResponse {
    private String message;
    private int status = 200;
    private List<String> data = new ArrayList<>();

    public void addData(String data) {
        this.data.add(data);
    }

    public JsonResponse(String message){
        this.message = message;
    }

    public String toGson(){
        Gson g = new Gson();
        return g.toJson(this);
    }


}
