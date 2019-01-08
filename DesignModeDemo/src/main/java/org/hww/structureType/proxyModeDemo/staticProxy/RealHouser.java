package org.hww.staticProxy;

//实现类
public class RealHouser implements  IHouse{


    @Override
    public void contract() {
        System.out.println("签合同");
    }
}
