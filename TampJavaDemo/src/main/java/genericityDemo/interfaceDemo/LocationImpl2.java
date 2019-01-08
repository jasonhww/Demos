package genericityDemo.interfaceDemo;

/**
 * 泛型类实现泛型接口
 */
public class LocationImpl2<T, K, U> implements ILocation<U> {

    //把第三个泛型变量U用来填充接口
    private U z;
    private T x;
    private K y;

    @Override
    public U getZ() {
        return null;
    }

    @Override
    public void setZ(U z) {

    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public K getY() {
        return y;
    }

    public void setY(K y) {
        this.y = y;
    }
}
