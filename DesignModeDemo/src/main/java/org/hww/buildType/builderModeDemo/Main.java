package org.hww.buildType.builderModeDemo;

import org.hww.buildType.builderModeDemo.normalBuilder.Phone;
import org.hww.buildType.builderModeDemo.standardBuilder.Director;
import org.hww.buildType.builderModeDemo.standardBuilder.Phone2;

public class Main {
    public static void main(String[] args) {
        //通过一般的建造者模式
        Phone phone = new Phone.Builder()
                .name("OPPO")
                .model("FINDX")
                .Ram("128G")
                .build();
        System.out.println(phone.toString());
        System.out.println("--------------------------------");
        //通过标准的建造者模式
        Phone2.Builder builder = new Phone2.Builder();
        Director director = new Director(builder);
        Phone2 phone2 = director.createPhone("MI","4X","68G");
        System.out.println(phone2.toString());
    }
}
