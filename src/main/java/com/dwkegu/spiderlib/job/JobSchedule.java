package com.dwkegu.spiderlib.job;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class JobSchedule {
    ThreadPoolExecutor executor = null;
    private static JobSchedule instance = null;
    private BlockingDeque<Runnable> deque = null;
    private JobSchedule(){
        deque = new LinkedBlockingDeque<>();
        executor = new ThreadPoolExecutor(2, 8, 1L, SECONDS, deque);
    }
    public static JobSchedule getInstance(){
        if(null == instance){
            instance = new JobSchedule();
        }
        return instance;
    }

    public void addJob(Runnable job){
        instance.executor.execute(job);
    }

}
