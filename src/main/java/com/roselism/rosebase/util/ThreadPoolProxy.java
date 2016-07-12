package com.roselism.rosebase.util;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by simon on 16-7-8.
 */
public class ThreadPoolProxy extends AbstractExecutorService {
    ThreadPoolExecutor mExecutor; //代理目标

    /**
     * 创建一个默认的代理类
     */
    public ThreadPoolProxy() {
        initExecutor();
    }

    /**
     * 指定需要代理的executor
     *
     * @param executor
     */
    public ThreadPoolProxy(@NonNull ThreadPoolExecutor executor) {
        Preconditions.checkNotNull(executor);
        mExecutor = executor;
    }

    private void initExecutor() {
        // 双重检查 (double check),只有在第一次创建的时候加锁
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated())
            synchronized (ThreadPoolProxy.this) {
                int corePoolSize = 3; // 核心数
                int maximumPoolSize = 6; // 最大线程数,在有序队列中，如果队列放满，则会增加核心数,上线就是这个值
                long keepAliveTime = 100;
                TimeUnit unit = TimeUnit.MILLISECONDS;
                BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
                ThreadFactory threadFactory = Executors.defaultThreadFactory();
                RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

                mExecutor = new ThreadPoolExecutor(
                        corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
            }
    }

    @Override
    public void shutdown() {
        mExecutor.shutdown();
    }

    @NonNull
    @Override
    public List<Runnable> shutdownNow() {
        return mExecutor.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return mExecutor.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return mExecutor.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return mExecutor.awaitTermination(timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        initExecutor();
        mExecutor.execute(command);
    }

    @Override
    public Future<?> submit(Runnable task) {
        initExecutor();
        return mExecutor.submit(task);
    }

    public void cancle(Runnable task) {
        initExecutor();
        mExecutor.remove(task);
    }
}
