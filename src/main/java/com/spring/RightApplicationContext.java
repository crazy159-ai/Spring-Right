package com.spring;

import com.sun.source.util.SourcePositions;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;

public class RightApplicationContext {

    private Class configClass;

    public RightApplicationContext(Class configClass){
        this.configClass = configClass;

        // 解析配置类
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();
        System.out.println(path);

        path = path.replace(".","/");
        // 扫描
        // Bootstrap ---> jre/lib
        // Ext ---------> jre/ext/lib
        // App ---------> classpath --->
        ClassLoader classLoader = RightApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getFile());
        if(file.isDirectory()){

            File[] files = file.listFiles();
            for (File f : files) {
                String fileName = f.getAbsolutePath();
                if(fileName.endsWith(".class")) {
                    String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                    className = className.replace("\\", ".");
                    System.out.println(className);
                    
                    try{

                        Class<?> claZZ = classLoader.loadClass(className);
                        if(claZZ.isAnnotationPresent(Component.class)){

                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public Object getBean(String beanName){
        return null;
    }
}
