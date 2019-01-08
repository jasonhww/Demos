package org.jasonhww.threaddemo.threeMethodThread;

public class MyRunnable implements Runnable {


    @Override
    public void run() {
        System.out.println("实现Runnable接口,重写run方法");
    }

    public static void main(String[] args) {
        new Thread(new MyRunnable()).start();
    }
}
