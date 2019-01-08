package reflectionDemo.normalClassAroundInfo;

import reflectionDemo.normalClassAroundInfo.bean.Person;
import reflectionDemo.normalClassAroundInfo.bean.PersonImpl;

import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        //以PersonImpl为基准
        Class<?> clazz1 = Class.forName("reflectionDemo.normalClassAroundInfo.bean.PersonImpl");
        getClassObjectMethod();
        System.out.println("------------------------");
        getClassAndPackageInfo(clazz1);
        System.out.println("-------------------------");
        getSuperClassInfo(clazz1);
        System.out.println("-------------------------");
        getInterfacesInfo(clazz1);
        getInterfacesInfo(Person.class);
        System.out.println("-------------------------");
        getModifiers(clazz1);
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("-------------------------");



    }


    private static void getClassObjectMethod() throws ClassNotFoundException {
        PersonImpl PersonImpl = new PersonImpl();
        Class<?> clazz1 = PersonImpl.class;
        Class<?> clazz2 = PersonImpl.getClass();
        Class<?> clazz3 = Class.forName("reflectionDemo.normalClassAroundInfo.bean.PersonImpl");
        //Class<?> clazz4 = getClass().getClassLoader().loadClass("PersonImpl");
        //boolean result = (clazz1 == clazz2 && clazz3 == clazz4 && clazz1 == clazz3);
        boolean result = (clazz1 == clazz2 && clazz1 == clazz3);
        System.out.println(result);
    }


    private static void getClassAndPackageInfo(Class<?> clazz1) throws ClassNotFoundException {
        String name = clazz1.getName();
        String simpleName = clazz1.getSimpleName();
        Package packages = clazz1.getPackage();
        System.out.println("类的全路径:" + name);
        System.out.println("类名:" + simpleName);
        System.out.println("包名:" + packages.getName());
    }

    private static void getSuperClassInfo(Class<?> clazz1) throws ClassNotFoundException {
        Class<?> superClass = clazz1.getSuperclass();
        System.out.println("超类的名:" + superClass.getName());
    }

    private static void getInterfacesInfo(Class<?> clazz1) throws ClassNotFoundException {
        Class<?>[] interfacesClass = clazz1.getInterfaces();
        StringBuilder builder = new StringBuilder();
        for (Class clazz : interfacesClass) {
            builder.append(clazz.getName());
            builder.append(",");
        }
        System.out.println(clazz1.getSimpleName()+"直接继承的接口名:" + builder.toString());


    }

    private static void getModifiers(Class<?> clazz1) throws ClassNotFoundException {
        int modifier = clazz1.getModifiers();
        System.out.println("访问修饰符是:"+Modifier.toString(modifier));
        System.out.println("是否存在Public修饰符:"+Modifier.isPublic(modifier));
    }
}

