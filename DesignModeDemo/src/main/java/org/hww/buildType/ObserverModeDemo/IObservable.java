package org.hww.buildType.ObserverModeDemo;

public interface IObservable {

    void add(IObserver iObserver);

    void delete(IObserver iObserver);
    //通知更新
    void notify(String msg);
}
