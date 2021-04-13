package com.cefeon.busytime;

public class Log {
    private Log(){}
    public static void info(String message){
        System.out.println(message);
    }

    public static void error(String message){
        System.out.println(message);
    }
}
