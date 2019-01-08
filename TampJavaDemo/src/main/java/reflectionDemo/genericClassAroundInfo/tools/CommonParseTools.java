package reflectionDemo.genericClassAroundInfo.tools;

import java.lang.reflect.*;

public class CommonParseTools {

    public static void parseClass(Class<?> c) {
        parseTypeParameters(c.getGenericInterfaces());
    }

    private static void parseTypeParameters(Type[] types) {
        for (Type type : types) {
            parseTypeParameter(type);
        }
    }

    private static void parseTypeParameter(Type type) {
        if (type instanceof Class) {
            Class<?> c = (Class<?>) type;
            System.out.println(c.getSimpleName());
        } else if (type instanceof TypeVariable) {
            TypeVariable<?> tv = (TypeVariable<?>) type;
            System.out.println(tv.getName());
            parseTypeParameters(tv.getBounds());
        } else if (type instanceof WildcardType) {
            WildcardType wt = (WildcardType) type;
            System.out.println("?");
            parseTypeParameters(wt.getUpperBounds());
            parseTypeParameters(wt.getLowerBounds());
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Type t = pt.getOwnerType();
            if (t != null) {
                parseTypeParameter(t);
            }
            parseTypeParameter(pt.getRawType());
            parseTypeParameters(pt.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            GenericArrayType arrayType = (GenericArrayType) type;
            Type t = arrayType.getGenericComponentType();
            parseTypeParameter(t);
        }

    }
}
