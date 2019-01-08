package genericityDemo.interfaceDemo;

/**
 * 非泛型类实现接口
 */
public class LocationImpl1 implements ILocation<String>{

    @Override
    public String getZ() {
        return null;
    }


    @Override
    public void setZ(String z) {

    }
}
