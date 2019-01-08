package org.hww.staticProxy;

//定义一个代理类来代理实现类
public class ProxyHouser implements IHouse {

    private IHouse iHouse;

    public ProxyHouser(IHouse iHouse) {
        this.iHouse = iHouse;
    }

    @Override
    public void contract() {
        before();
        iHouse.contract();
        after();
    }


    private void before() {
        System.out.println("联系看房");
    }


    private void after() {
        System.out.println("收取佣金");
    }
}
