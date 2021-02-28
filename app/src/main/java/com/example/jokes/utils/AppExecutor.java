package com.example.jokes.utils;

import android.app.Application;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppExecutor extends Application {
    /****
     * Get the number of cores in runtime (The system will scale the work depending on the number of available cores
     */
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    /****
     * Instantiate queues
     */
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static final int KEEP_ALIVE_TIME = 6;

    /****
     * Create a thread pool manager
     */
    public ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            NUMBER_OF_CORES,
            NUMBER_OF_CORES,
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            workQueue
    );
}
