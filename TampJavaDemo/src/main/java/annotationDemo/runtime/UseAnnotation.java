package annotationDemo.runtime;

import java.lang.reflect.Method;

public class UseAnnotation {

    @Name(value = "小学僧")
    public String getName() {
        return "什么都没有";
    }

    public String getFinalName() {
        return "什么都没有";
    }

    public static void main(String[] args) {
        //通过反射处理
        Method[] methods = UseAnnotation.class.getDeclaredMethods();
        for (Method method : methods) {
            Name name = method.getAnnotation(Name.class);
            System.out.println("注解的值:"+name.value());
        }
    }
}

