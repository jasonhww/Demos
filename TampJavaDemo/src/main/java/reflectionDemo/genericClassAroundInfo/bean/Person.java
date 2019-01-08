package reflectionDemo.genericClassAroundInfo.bean;

import java.io.Serializable;

public class Person<T extends Serializable>
        extends GenericPersonFather<String>
        implements GenericIPerson<T,String>,GenericIPersonArray<Integer[]>,
        GenericIPersonWildcard<Comparable<? extends Number>> {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String work(T text) {
        return null;
    }

    @Override
    public void isArray(Integer[] text) {

    }

    @Override
    public void isWildcard(Comparable<? extends Number> text) {

    }
}
