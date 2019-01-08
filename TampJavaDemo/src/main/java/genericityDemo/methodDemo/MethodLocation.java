package genericityDemo.methodDemo;

import java.util.Arrays;

/**
 * 泛型函数的定义
 */
public class MethodLocation {

    public static <T> void staticMethod(T text) {
        System.out.println("staticMethod：" + text);
    }

    //返回值与形参中存在泛型
    public <T> T normalMethod(T text) {
        System.out.println("normalMethod:" + text);
        return text;
    }

    //定义泛型数组, T[]相当于String[].
    public static <T> T[] funArray(T... arg) {  // 接收可变参数
        return arg;            // 返回泛型数组
    }

    public static void main(String[] args) {
        MethodLocation methodLocation = new MethodLocation();
        String text1 = methodLocation.normalMethod("hello");//方法1
        String text2 = methodLocation.<String>normalMethod("genericity");//方法2
        System.out.println("from main method:"+text1);
        System.out.println("--------------------------");
        Integer[] intArray = MethodLocation.funArray(1, 2, 3, 4, 5, 6);
        System.out.println(Arrays.toString(intArray));
    }


}