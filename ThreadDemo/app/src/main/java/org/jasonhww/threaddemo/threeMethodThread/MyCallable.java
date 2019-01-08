package org.jasonhww.threaddemo.threeMethodThread;

import java.util.concurrent.*;

/**
 * 实现线程方法之Callable接口.
 * Future以及FutureTask的示例
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("子线程正在干活");
        Thread.sleep(3000);
        return "实现Callable接口,重写Call方法";
    }

    public static void main(String[] args) throws Exception {
       useFuture();
        //useFutureTask();
    }



    private static void  useFuture() throws InterruptedException, ExecutionException {
        MyCallable myCallable = new MyCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //提交任务
        Future<String> future = executorService.submit(myCallable);
        executorService.shutdown();

        Thread.sleep(1000);//模拟正在干活
        System.out.println("主线程正在干活");
        //阻塞当前线程,等待返回结果.
        System.out.println("等待返回结果：" + future.get());
        System.out.println("主线程所有的活都干完了");
    }

    private static void  useFutureTask() throws InterruptedException, ExecutionException {
        MyCallable myCallable = new MyCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //定义一个Task,再提交任务.
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        executorService.submit(futureTask);

        executorService.shutdown();

        Thread.sleep(1000);//模拟正在干活
        System.out.println("主线程正在干活");
        //阻塞当前线程,等待返回结果.
        System.out.println("等待返回结果：" + futureTask.get());
        System.out.println("主线程所有的活都干完了");
    }


}
