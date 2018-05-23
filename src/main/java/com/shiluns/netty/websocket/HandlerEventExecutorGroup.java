package com.shiluns.netty.websocket;

import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.MultithreadEventExecutorGroup;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.concurrent.RejectedExecutionHandlers;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import io.netty.util.internal.SystemPropertyUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fengchuanbo
 * @date 2018/5/20
 */
public class HandlerEventExecutorGroup extends MultithreadEventExecutorGroup {

    private static final int DEFAULT_EVENT_LOOP_THREADS;

    static {
        DEFAULT_EVENT_LOOP_THREADS = Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    }

    private ThreadPoolExecutor threadPoolExecutor;

    public HandlerEventExecutorGroup(int corePoolSize, int maximumPoolSize, long keepAliveTime){
        this(0,corePoolSize,maximumPoolSize,keepAliveTime);
    }

    public HandlerEventExecutorGroup(int nThreads, int corePoolSize, int maximumPoolSize, long keepAliveTime){
        this(nThreads,corePoolSize,maximumPoolSize,keepAliveTime,new LinkedBlockingQueue<>(nThreads <= 0 ? 16 : nThreads),new BussinessThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public HandlerEventExecutorGroup(int nThreads, int corePoolSize, int maximumPoolSize, long keepAliveTime, BlockingQueue<Runnable> workQueue){
        this(nThreads,corePoolSize,maximumPoolSize,keepAliveTime,workQueue,new BussinessThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public HandlerEventExecutorGroup(int nThreads, int corePoolSize, int maximumPoolSize, long keepAliveTime, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, java.util.concurrent.RejectedExecutionHandler handler) {
        super(nThreads  <= 0 ? DEFAULT_EVENT_LOOP_THREADS : nThreads, new HandlerThreadFactory(), 300, RejectedExecutionHandlers.backoff(30, 50, TimeUnit.MILLISECONDS));
        initThreadPool(corePoolSize,maximumPoolSize,keepAliveTime,workQueue,threadFactory,handler);
    }

    private void initThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, java.util.concurrent.RejectedExecutionHandler handler){
        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.MILLISECONDS,workQueue,threadFactory,handler);
    }

    @Override
    protected EventExecutor newChild(Executor executor, Object... args) throws Exception {
        return new HandlerEventExecutor(this, executor, (Integer) args[0], (RejectedExecutionHandler) args[1]);
    }

    class HandlerEventExecutor extends SingleThreadEventExecutor {

        public HandlerEventExecutor() {
            this((EventExecutorGroup) null);
        }

        public HandlerEventExecutor(ThreadFactory threadFactory) {
            this(null, threadFactory);
        }

        public HandlerEventExecutor(Executor executor) {
            this(null, executor);
        }

        public HandlerEventExecutor(EventExecutorGroup parent) {
            this(parent, new DefaultThreadFactory(DefaultEventExecutor.class));
        }

        public HandlerEventExecutor(EventExecutorGroup parent, ThreadFactory threadFactory) {
            super(parent, threadFactory, true);
        }

        public HandlerEventExecutor(EventExecutorGroup parent, Executor executor) {
            super(parent, executor, true);
        }

        public HandlerEventExecutor(EventExecutorGroup parent, ThreadFactory threadFactory, int maxPendingTasks,
                                    RejectedExecutionHandler rejectedExecutionHandler) {
            super(parent, threadFactory, true, maxPendingTasks, rejectedExecutionHandler);
        }

        public HandlerEventExecutor(EventExecutorGroup parent, Executor executor, int maxPendingTasks,
                                    RejectedExecutionHandler rejectedExecutionHandler) {
            super(parent, executor, true, maxPendingTasks, rejectedExecutionHandler);
        }

        @Override
        protected void run() {
            for (;;) {
                Runnable task = takeTask();
                if (task != null) {
                    threadPoolExecutor.execute(() -> task.run());
                    updateLastExecutionTime();
                }
            }
        }

    }

    @Override
    public Future<?> shutdownGracefully() {
        return super.shutdownGracefully().addListener(future -> {
            if (future != null && future.isDone()){
                threadPoolExecutor.shutdown();
            }
        });
    }

    static class BussinessThreadFactory implements ThreadFactory {

        private final AtomicInteger nextId = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, BussinessThreadFactory.class.getSimpleName() + nextId.incrementAndGet());
        }
    }

    static class HandlerThreadFactory implements ThreadFactory {

        private final AtomicInteger nextId = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, HandlerEventExecutorGroup.class.getSimpleName() + nextId.incrementAndGet());
        }
    }
}
