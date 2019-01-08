package reflectionDemo.genericClassAroundInfo;

import reflectionDemo.genericClassAroundInfo.bean.Person;
import reflectionDemo.genericClassAroundInfo.tools.CommonParseTools;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> personClazz = Person.class;
        getGenericSuperClassInfo(personClazz);
        System.out.println("-----------------");
        geGenericInterfacesParseTypeVariable(personClazz);
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        CommonParseTools.parseClass(personClazz);
    }


    /**
     * 获取泛型超类信息
     */
    private static void getGenericSuperClassInfo(Class<?> clazz1) throws ClassNotFoundException {
        Type type = clazz1.getGenericSuperclass();
        //演示ParameterizedType的解析
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypes = parameterizedType.getActualTypeArguments();
            for (Type type1 : actualTypes) {
                if (type1 instanceof Class) {
                    Class clazz = (Class) type1;
                    System.out.println("填充的类型为:" + clazz.getName());
                }
            }
            Class clazzRaw = (Class) parameterizedType.getRawType();

            System.out.println("原类型为:" + clazzRaw.getName());
        }
    }

    /**
     * 获取泛型接口信息
     */
    private static void geGenericInterfacesParseTypeVariable(Class<?> clazz1) throws ClassNotFoundException {
        Type[] interfacesType = clazz1.getGenericInterfaces();
        for (Type typeInterface : interfacesType) {
            if (typeInterface instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) typeInterface;
                //获取泛型接口的参数列表
                Type[] actualTypes = parameterizedType.getActualTypeArguments();
                for (Type actualType : actualTypes) {
                    /*
                     *   解析第一个接口信息
                     */
                    //针对T,演示TypeVariable的解析
                    if (actualType instanceof TypeVariable) {
                        TypeVariable typeVariable = (TypeVariable) actualType;
                        System.out.println("填充类型为:" + typeVariable.getName());
                        Type[] typeBounds = typeVariable.getBounds();
                        for (Type typeBound : typeBounds) {
                            if (typeBound instanceof Class) {
                                Class clazz = (Class) typeBound;
                                //如果未设定，则输出Object.
                                System.out.println("TypeVariable上边界:" + clazz.getName());
                            }
                        }
                    }


                    //针对Class
                    if (actualType instanceof Class) {
                        Class clazz = (Class) actualType;
                        System.out.println("填充类型为:" + clazz.getName());
                    }

                    /*
                     *   解析第二个接口信息
                     */

                    //针对Array,演示GenericArrayType解析
                    if (actualType instanceof GenericArrayType) {
                        GenericArrayType genericArrayType = (GenericArrayType) actualType;
                        Type typeComponent = genericArrayType.getGenericComponentType();
                        if (typeComponent instanceof Class) {
                            Class clazz = (Class) typeComponent;
                            System.out.println("数组的填充类型为:" + clazz.getName());
                        }
                    }

                    /*
                     *   解析第三个接口信息
                     */
                    if (actualType instanceof ParameterizedType) {
                        ParameterizedType parameterizedType1 = (ParameterizedType) actualType;
                        Type[] actualTypeArguments = parameterizedType1.getActualTypeArguments();
                        for (Type actualTypeArgument : actualTypeArguments) {
                            //针对通配符,演示WildcardType解析
                            if (actualTypeArgument instanceof WildcardType) {
                                WildcardType wildcardType = (WildcardType) actualTypeArgument;
                                System.out.println("填充类型为:" + wildcardType.getTypeName());
                                Type[] upperBoundsTypes = wildcardType.getUpperBounds();
                                for (Type upperBoundsType : upperBoundsTypes) {
                                    if (upperBoundsType instanceof Class) {
                                        Class clazz = (Class) upperBoundsType;
                                        System.out.println("wildcardType上边界为:" + clazz.getName());
                                    }
                                }

                                Type[] lowerBoundsTypes = wildcardType.getLowerBounds();
                                for (Type lowerBoundsType : lowerBoundsTypes) {
                                    if (lowerBoundsType instanceof Class) {
                                        Class clazz = (Class) lowerBoundsType;
                                        System.out.println("wildcardType下边界为:" + clazz.getName());
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }


}

