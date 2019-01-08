package org.jasonhww.threaddemo.threadPollDm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池示例
 */
public class HandThreadPoll {


    public static void main(String[] args) {
//        executeFixedThreadPool();
//        executeSingleThreadExecutor();
//        executeCachedThreadPool();
        executeScheduledThreadPool(2);
    }

    private static void executeFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程:" + Thread.currentThread().getName());
                }
            });
        }
    }


    private static void executeSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程:" + Thread.currentThread().getName());
                }
            });
        }
    }


    private static void executeScheduledThreadPool(int flag) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("后"+System.currentTimeMillis());
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("线程:" + Thread.currentThread().getName());

            }
        };
        System.out.println("前"+System.currentTimeMillis());
        switch (flag) {
            case 0:
                //延迟1000毫秒后开始执行
                executorService.schedule(runnable, 1000, TimeUnit.MILLISECONDS);
                break;
            case 1:
                //延迟1000毫秒后开始执行,后面每隔2000毫秒执行一次.强调任务的执行频率,不受任务执行时间影响,过时不候.
                executorService.scheduleAtFixedRate(runnable, 1000, 2000, TimeUnit.MILLISECONDS);
                break;
            case 2:
                //延迟1000毫秒后开始执行,后面每次延迟3000毫秒执行一次.强调任务执行的间隔.
                executorService.scheduleWithFixedDelay(runnable, 1000, 3000, TimeUnit.MILLISECONDS);

                break;
            default:
                break;
        }
    }

    private static void executeCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程:" + Thread.currentThread().getName());
                }
            });
        }
    }

}
