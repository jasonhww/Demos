package org.hww.buildType.ObserverModeDemo;

import java.util.ArrayList;
import java.util.List;

public class Observable implements IObservable {

    private List<IObserver> iobserverList = new ArrayList<>();


    @Override
    public void add(IObserver iObserver) {
        iobserverList.add(iObserver);
    }

    @Override
    public void delete(IObserver iObserver) {
        iobserverList.remove(iObserver);
    }


    @Override
    public void notify(String msg) {
        for (IObserver iObserver : iobserverList) {//通知所有观察者
            iObserver.notify(msg);
        }
    }
}
