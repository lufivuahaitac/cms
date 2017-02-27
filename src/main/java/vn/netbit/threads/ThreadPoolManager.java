/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.netbit.threads;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Ba Y
 */
public final class ThreadPoolManager {

    protected static final Logger LOGGER = Logger.getLogger(ThreadPoolManager.class.getName());

    private static final class RunnableWrapper implements Runnable {

        private final Runnable runnable;

        public RunnableWrapper(final Runnable r) {
            runnable = r;
        }

        @Override
        public final void run() {
            try {
                runnable.run();
            } catch (final Throwable e) {
                final Thread t = Thread.currentThread();
                final Thread.UncaughtExceptionHandler h = t.getUncaughtExceptionHandler();
                if (h != null) {
                    h.uncaughtException(t, e);
                }
            }
        }
    }
    private final ScheduledThreadPoolExecutor scheduledThreadPool;
    private final ThreadPoolExecutor instantThreadPool;
    /**
     * temp workaround for VM issue
     */
    private static final long MAX_DELAY = Long.MAX_VALUE / 1000000 / 2;
    private boolean shutdown;

    int THREAD_CORE_POOL_SIZE = 8;

    /**
     *
     */
    private ThreadPoolManager() {
        final int scheduledPoolSize = Math.max(1, THREAD_CORE_POOL_SIZE / 3);
        final int instantPoolSize = THREAD_CORE_POOL_SIZE - scheduledPoolSize;
        scheduledThreadPool = new ScheduledThreadPoolExecutor(scheduledPoolSize, new ThreadPoolManager.PriorityThreadFactory("ScheduledThread", Thread.NORM_PRIORITY));
        instantThreadPool = new ThreadPoolExecutor(instantPoolSize, instantPoolSize, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolManager.PriorityThreadFactory("InstantThread", Thread.NORM_PRIORITY));
        scheduleGeneralAtFixedRate(new ThreadPoolManager.PurgeTask(), 10 * 60 * 1000l, 5 * 60 * 1000l);
    }

    public static long validateDelay(long delay) {
        if (delay < 0) {
            delay = 0;
        } else if (delay > MAX_DELAY) {
            delay = MAX_DELAY;
        }
        return delay;
    }

    /**
     *
     * @param r
     * @param delay
     * @return
     */
    public ScheduledFuture<?> scheduleGeneral(Runnable r, long delay) {
        try {
            delay = ThreadPoolManager.validateDelay(delay);
            return scheduledThreadPool.schedule(new ThreadPoolManager.RunnableWrapper(r), delay, TimeUnit.SECONDS);
        } catch (RejectedExecutionException e) {
            return null;
            /* shutdown, ignore */

        }
    }

    /**
     *
     * @param r
     * @param initial
     * @param delay
     * @return
     */
    public ScheduledFuture<?> scheduleGeneralAtFixedRate(Runnable r, long initial, long delay) {
        try {
            delay = ThreadPoolManager.validateDelay(delay);
            initial = ThreadPoolManager.validateDelay(initial);
            return scheduledThreadPool.scheduleAtFixedRate(new ThreadPoolManager.RunnableWrapper(r), initial, delay, TimeUnit.SECONDS);
        } catch (RejectedExecutionException e) {
            return null;
            /* shutdown, ignore */

        }
    }

    @Deprecated
    public boolean removeGeneral(RunnableScheduledFuture<?> r) {
        return scheduledThreadPool.remove(r);
    }

    @Deprecated
    public boolean removeGeneral(Runnable r) {
        return scheduledThreadPool.remove(r);
    }

    /**
     *
     * @param r
     */
    public void execute(Runnable r) {
        instantThreadPool.execute(r);
    }

    public String[] getStats() {
        return new String[]{
            "STP:",
            " + General Scheduled:",
            " |- ActiveThreads:   " + scheduledThreadPool.getActiveCount(),
            " |- getCorePoolSize: " + scheduledThreadPool.getCorePoolSize(),
            " |- PoolSize:        " + scheduledThreadPool.getPoolSize(),
            " |- MaximumPoolSize: " + scheduledThreadPool.getMaximumPoolSize(),
            " |- CompletedTasks:  " + scheduledThreadPool.getCompletedTaskCount(),
            " |- ScheduledTasks:  " + (scheduledThreadPool.getTaskCount() - scheduledThreadPool.getCompletedTaskCount()),
            " | -------",
            " + General Tasks:",
            " |- ActiveThreads:   " + instantThreadPool.getActiveCount(),
            " |- getCorePoolSize: " + instantThreadPool.getCorePoolSize(),
            " |- MaximumPoolSize: " + instantThreadPool.getMaximumPoolSize(),
            " |- LargestPoolSize: " + instantThreadPool.getLargestPoolSize(),
            " |- PoolSize:        " + instantThreadPool.getPoolSize(),
            " |- CompletedTasks:  " + instantThreadPool.getCompletedTaskCount(),
            " |- QueuedTasks:     " + instantThreadPool.getQueue().size(),
            " | -------"
        };
    }

    private static class PriorityThreadFactory implements ThreadFactory {

        private final int _prio;
        private final String _name;
        private final AtomicInteger _threadNumber = new AtomicInteger(1);
        private final ThreadGroup _group;

        public PriorityThreadFactory(String name, int prio) {
            _prio = prio;
            _name = name;
            _group = new ThreadGroup(_name);
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(_group, r);
            t.setName(_name + "-" + _threadNumber.getAndIncrement());
            t.setPriority(_prio);
            return t;
        }

        public ThreadGroup getGroup() {
            return _group;
        }
    }

    public void shutdown() {
        shutdown = true;
        try {
            scheduledThreadPool.awaitTermination(1, TimeUnit.SECONDS);
            instantThreadPool.awaitTermination(1, TimeUnit.SECONDS);
            scheduledThreadPool.shutdown();
            instantThreadPool.shutdown();
            LOGGER.info("All ThreadPools are now stopped");

        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, "", e);
        }
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void purge() {
        scheduledThreadPool.purge();
        instantThreadPool.purge();
    }

    private class PurgeTask implements Runnable {

        @Override
        public void run() {
            scheduledThreadPool.purge();
        }
    }

    private static final class SingletonHolder {

        private static final ThreadPoolManager INSTANCE = new ThreadPoolManager();
    }

    public static ThreadPoolManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
