package annotationDemo.compile;

import annotationDemo.runtime.Name;

import java.lang.reflect.Method;

public class UseAnnotation {

    @injectString
    private String name;

    @injectInt
    private int age;

}

