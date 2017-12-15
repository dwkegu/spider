package com.dwkegu.spiderlib;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger {
    private static Logger myLogger;
    private static FileHandler handler;
    private static FileLogger instance;
    private static String logFile = "f:/tmp/jLog.log";
    static {
        myLogger = Logger.getLogger("myLog");
        try {
            handler = new FileHandler(logFile);
        } catch (IOException e) {
            handler = null;
            e.printStackTrace();
        }
        if(handler!=null){
            handler.setFormatter(new SimpleFormatter());
            myLogger.addHandler(handler);
        }else{
            myLogger = null;
        }
    }
    public static void d(String tag, String info){
        if(myLogger==null){
            return;
        }
        myLogger.info(tag+":"+info);
    }
}
