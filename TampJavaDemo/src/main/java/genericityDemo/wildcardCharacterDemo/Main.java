package genericityDemo.wildcardCharacterDemo;

import genericityDemo.classDemo.Location;

public class Main {

    //通配符仅仅是填充泛型变量的一种方式.
    public static void main(String[] args) {
        //有且仅能使用的地方,只能使用在生成泛型实例时.
        Location<?> location;
        location = new Location<Integer>(2,3);

    }

}
