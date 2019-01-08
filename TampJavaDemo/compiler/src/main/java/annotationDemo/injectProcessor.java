package annotationDemo;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

//指定支持的注解类型和源码版本
@SupportedAnnotationTypes({"com.cjy.lhk_annotations.mylhk,injectInt",
        "com.cjy.lhk_annotations.mylhk.injectString"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)//注册该注解处理器
public class injectProcessor extends AbstractProcessor {

    private static final ClassName CONTEXT =
            ClassName.get("android.content", "Context");


    //待生成java文件的的集合，key为被注解的类的类名，value为GenerateJavaFile对象
    private HashMap<String, GenerateJavaFile> mGenerateJavaFiles = new HashMap<>();


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for (TypeElement typeElement : set) {//获取所有注解类对应的TypeElement
            //获取注解类对应被注解的对应元素,
            //比如注解被用在某个成员变量上,那么这个就是获取成员变量对应的元素
            for (Element element : roundEnvironment.getElementsAnnotatedWith(typeElement)) {
                addElementToGenerateJavaFile(element);
            }
        }
        createJavaFile();
        return true;
    }

    /**
     * 添加一个被注解元素到对应的GenerateJavaFile对象中
     * (收集信息)
     *
     * @param element 被注解元素
     */
    private void addElementToGenerateJavaFile(Element element) {
        //获取element对应成员变量所在的类，即被注解的类
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();//获取外部元素
        //System.out.println("---getQualifiedName =---" + typeElement.getQualifiedName());
        String[] split = typeElement.getQualifiedName().toString().split("\\.");
        String className = split[split.length - 1];

        //通过父类的processingEnv获取报信者，用于在编译过程中打印log
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "add element to generate file " + className);

        //获取被注解类对应的GenerateJavaFile对象，如果没有，则创建
        GenerateJavaFile generateJavaFile = mGenerateJavaFiles.get(className);
        if (generateJavaFile == null) {
            GenerateJavaFile file = new GenerateJavaFile();
            //设置待生成java文件的包名
            file.packageName = processingEnv.getElementUtils().getPackageOf(element).toString();
            //设置待生成java文件的类名
            file.className = className + "_Inject";
            //初始化元素集合
            file.elements = new ArrayList<>();
            file.elements.add(element);
            //保存被注解类所对应要生成java类的GenerateJavaFile对象
            mGenerateJavaFiles.put(className, file);
        } else {
            //将注解元素添加到有的generateJavaFile对象中
            generateJavaFile.elements.add(element);
        }
    }

    /**
     * 生成java文件
     */
    private void createJavaFile() {
        //遍历GenerateJavaFile集合
        for (String className : mGenerateJavaFiles.keySet()) {

            //获取一个GenerateJavaFile对象
            GenerateJavaFile file = mGenerateJavaFiles.get(className);

            //构建一个构造方法，该构造方法带有一个Context类型的参数
            MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(CONTEXT, "context");

            //遍历该类中需要处理的注解元素
            for (Element element : file.elements) {
                //如果注解的成员变量是一个int类型
                if (element.asType().toString().equals("int")) {
                    //在构造方法中添加一条语句
                    //例如：((MainActivity)context).one = context.getResources().getInteger(R.integer.one);
                    builder.addStatement("(($N)context).$N = context.getResources().getInteger(R.integer.$N)",
                            file.className.split("_")[0], element.getSimpleName(), element.getSimpleName());
                    //如果注解的是一个String类型
                } else if (element.asType().toString().equals("java.lang.String")) {
                    //在构造方法中添加一条语句
                    //例如：((MainActivity)context).hello = context.getResources().getString(R.string.hello);
                    builder.addStatement("(($N)context).$N = context.getResources().getString(R.string.$N)",
                            file.className.split("_")[0], element.getSimpleName(), element.getSimpleName());
                }
            }
            //构建一个类，添加一个上述的构造方法
            TypeSpec typeSpec = TypeSpec.classBuilder(file.className)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(builder.build())
                    .build();
            try {
                //输出java文件
                JavaFile javaFile = JavaFile.builder(file.packageName, typeSpec).build();
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 待生成的Java文件信息
     */
    private static class GenerateJavaFile {
        String packageName;//包名
        String className;//类名
        List<Element> elements;//程序元素集合
    }
}
