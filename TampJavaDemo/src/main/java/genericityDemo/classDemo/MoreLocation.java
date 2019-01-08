package genericityDemo.classDemo;

/**
 * 多泛型变量类的定义
 * 以,分隔
 */
public class MoreLocation<T, U> {//标识符号可以随意写

    private T x;
    private U y;

    public void setX(T x) {//作为参数
        this.x = x;
    }

    public void setY(U y) {
        this.y = y;
    }

    public T getX() {//作为返回值
        return this.x;
    }

    public U getY() {
        return this.y;
    }

}




