package genericityDemo.typeBindDemo;

import java.io.Serializable;

public class TypeBind {

    public static void main(String[] args) {
        Dog dog = new Dog("旺财");
        String nameClass = getAnimalNameClass(dog);
        System.out.println(nameClass);
        System.out.println("---------------");
        String actionInterface = getAnimalActionInterface(dog);
        System.out.println(actionInterface);
        System.out.println("---------------");
        String nameAction = getAnimalNameAndAction(dog, dog);
        System.out.println(nameAction);
    }

    //绑定类
    private static <T extends Animal> String getAnimalNameClass(T sub) {
        return sub.getName();
    }

    //绑定接口
    private static <T extends IAction> String getAnimalActionInterface(T sub) {
        return sub.howl();
    }

    //多个泛型绑定多个类型,通过&符号指定多个类型
    private static <T extends Animal & Serializable, U extends IAction & Serializable> String
    getAnimalNameAndAction(T sub1, U sub2) {
        return sub1.getName() + sub2.howl();
    }
}
