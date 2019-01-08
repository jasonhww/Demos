package org.jasonhww.threaddemo.threeMethodThread.interrupt;

public class MyRunnableInterrupt implements Runnable {
    private int i = 0;

    @Override
    public  void run() {
        //中断目标线程,并不是指立即停止线程,而仅仅只是将线程的标识为true,一般由目标线程自己去检测并决定是否终止线程,还是不理会中断.
        while (!Thread.currentThread().isInterrupted()) {
            i++;
            System.out.println("还没中断继续输出i:" + i);
        }
        System.out.println("stop!已经中断停止输出.");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnableInterrupt());
        thread.run();
        Thread.sleep(1);
        thread.interrupt();
    }
}
