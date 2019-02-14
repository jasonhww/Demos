package org.jasonhww.threaddemo.threeMethodThread.interrupt;

public class MyRunnableInterrupt implements Runnable {

    @Override
    public  void run() {
        //中断目标线程,并不是指立即停止线程,而仅仅只是将线程的标识为true,一般由目标线程自己去检测并决定是否终止线程.
        for (int j = 0; j <100000000 ; j++) {
            if (Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted!已经中断停止输出.开始收尾工作1");
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //处于阻塞状态的线程,也会立马被中断,所以就会抛出此异常.
                System.out.println("Interrupted!已经中断停止输出.开始收尾工作2");
                return;
            }
            System.out.println("还没中断继续输出j:" + j);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnableInterrupt());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
