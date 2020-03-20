package com.xhb.classloader;

public class ClassLoaderTreeDemo {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTreeDemo.class.getClassLoader();
        while (loader !=null){
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
        System.out.println(loader);

        System.out.println(ClassLoader.getSystemClassLoader());
    }
}

// log
// sun.misc.Launcher$AppClassLoader@18b4aac2
// sun.misc.Launcher$ExtClassLoader@7ea987ac
// null