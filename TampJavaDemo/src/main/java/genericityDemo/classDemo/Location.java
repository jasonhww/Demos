package genericityDemo.classDemo;

/**
 * 单泛型变量类的定义
 */
public class Location<T> {//标识符号可以随意写

    private T x;
    private T y;

    public Location() {
    }

    public Location(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public void setX(T x) {//作为参数
        this.x = x;
    }

    public void setY(T y) {
        this.y = y;
    }

    public T getX() {//作为返回值
        return this.x;
    }

    public T getY() {
        return this.y;
    }

}