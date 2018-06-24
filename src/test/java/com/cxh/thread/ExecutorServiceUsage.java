package com.cxh.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * JDK的ExecutorService用法
 */
public class ExecutorServiceUsage {

    /**
     * 实现策略一：创建一个线程
     */
    @Test
    public void example1(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(this::run);
        //关闭线程池，避免线程活动造成的资源浪费
        executorService.shutdown();
        run();
    }

    public void run(){
        for(int i=0;i<100;i++){
            System.out.println("线程运行中"+Thread.currentThread().getName());
        }
    }
    /**
     * 使用sumbit提交一个线程
     */
    @Test
    public void example2() throws ExecutionException, InterruptedException {
        //任意时间内，池里只能有一个线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(this::run);
        System.out.println("完成了吗？"+future.isDone());//完成状态可能是终止，或者正常完成
        run();
        executorService.shutdown();
    }

    /**
     * 使用Callable来提交一个线程，并拿到线程的返回结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void example3() throws ExecutionException, InterruptedException {
        //任意时间内，池里只能有一个线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new ThreadCallable());
        System.out.println("完成了吗？"+future.isDone());//完成状态可能是终止，或者正常完成
        run();
        //如果submit里面传入的是一个callable，get方法能拿到线程执行后的数据
        System.out.println("堵塞线程，直至线程结束，拿到线程的东西了:"+future.get());
        executorService.shutdown();
    }
    class ThreadCallable implements Callable {

        @Override
        public Object call() throws Exception {
            new ExecutorServiceUsage().run();
            return "完成";
        }
    }

}
