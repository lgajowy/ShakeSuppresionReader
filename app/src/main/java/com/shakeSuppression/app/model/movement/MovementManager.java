package com.shakeSuppression.app.model.movement;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MovementManager {

    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private static BlockingQueue<Runnable> movementQueue;
    private static ThreadPoolExecutor movesExecutor;

    static {
        movementQueue = new LinkedBlockingQueue<Runnable>();
        movesExecutor = new ThreadPoolExecutor(
                NUMBER_OF_CORES,
                NUMBER_OF_CORES,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                movementQueue);
    }

    private MovementManager() {

    }

    public static void runTask(Runnable task) {
        movesExecutor.execute(task);
    }
}
