package genericityDemo.typeBindDemo;

import java.io.Serializable;

public class Dog extends Animal implements IAction, Serializable {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String howl() {
        return "开始嚎叫";
    }
}
