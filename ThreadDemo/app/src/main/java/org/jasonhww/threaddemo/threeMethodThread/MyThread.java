package org.jasonhww.threaddemo.threeMethodThread;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("直接继承Thread,重写run方法");
    }

    public static void main(String[] args) {
        new MyThread().start();
    }
}
