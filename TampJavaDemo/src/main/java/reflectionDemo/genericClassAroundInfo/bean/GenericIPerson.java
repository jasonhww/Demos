package reflectionDemo.genericClassAroundInfo.bean;

public interface GenericIPerson<T, K> {
    K work(T text);
}
