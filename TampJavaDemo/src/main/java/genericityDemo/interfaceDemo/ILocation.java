package genericityDemo.interfaceDemo;

interface ILocation<T> {        // 在接口上定义泛型
    T getZ();

    void setZ(T z);
}
