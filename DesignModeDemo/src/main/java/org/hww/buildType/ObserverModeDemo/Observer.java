package org.hww.buildType.ObserverModeDemo;

public class Observer implements IObserver {
    long wbId;
    String wbName;

    public Observer(long wbId, String wbName) {
        this.wbId = wbId;
        this.wbName = wbName;
    }

    public long getWbId() {
        return wbId;
    }

    public void setWbId(long wbId) {
        this.wbId = wbId;
    }

    public String getWbName() {
        return wbName;
    }

    public void setWbName(String wbName) {
        this.wbName = wbName;
    }

    @Override
    public void notify(String msg) {
        System.out.println(wbName + "用户接收到通知内容:" + msg);
    }
}
