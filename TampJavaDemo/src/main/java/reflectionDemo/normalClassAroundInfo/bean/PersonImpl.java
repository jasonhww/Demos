package reflectionDemo.normalClassAroundInfo.bean;

public class PersonImpl extends Person implements IPerson {

    @Override
    public void work() {
        System.out.println("work");
    }
}
