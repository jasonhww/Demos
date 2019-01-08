package org.hww.buildType.ObserverModeDemo;

public class Main {
    public static void main(String[] args) {
        //准备两个微博用户
        Observer observer1 = new Observer(666,"james");
        Observer observer2 = new Observer(999,"wade");
        Observable observable = new Observable();
        //订阅企业微博
        observable.add(observer1);
        observable.add(observer2);
        //发布通知
        observable.notify("发布新产品了");
        System.out.println("-------------------------------------------");
        System.out.println("新产品出现问题,"+observer1.getWbName()+"取消了订阅");
        //取消订阅,再次发布通知
        observable.delete(observer1);
        observable.notify("发布改良产品了");
    }
}
