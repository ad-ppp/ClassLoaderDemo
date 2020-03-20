package com.xhb.classloader;

import com.xhb.classloader.classloader.FileSystemClassLoader;
import com.xhb.classloader.module.Person;

import java.io.File;

/**
 * class 对象 vs
 */
public class ClassVsDemo {
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir") + File.separator
                + "/class";
        String className = "com.xhb.classloader.module.Person";

        final Person person = new Person();

        final ClassLoader classLoader1 = new FileSystemClassLoader(dir);
        final ClassLoader classLoader2 = new FileSystemClassLoader(dir);

        try {
            final Class<?> class1 = classLoader1.loadClass(className);
            final Person p1 = (Person) class1.newInstance();
            p1.setAge(10);
            p1.setName("Jacky");

            final Class<?> class2 = classLoader2.loadClass(className);
            final Person p2 = (Person) class2.newInstance();
            p2.setAge(20);
            p2.setName("Alice");

            System.out.println(p1.toString());
            System.out.println(p2.toString());

            // AppClassLoader
            System.out.println("classLoader0:" + person.getClass().getClassLoader());
            // FileSystemClassLoader
            System.out.println("classLoader1:" + classLoader1);
            // FileSystemClassLoader
            System.out.println("classLoader2:" + classLoader2);

            System.out.println(p1.getClass().getClassLoader());     // sun.misc.Launcher$AppClassLoader@18b4aac2
            System.out.println(p2.getClass().getClassLoader());     // sun.misc.Launcher$AppClassLoader@18b4aac2
            System.out.println(String.format("class0 == class1 ? %b", person.getClass() == class1));
            System.out.println(String.format("class1 == class2 ? %b", class1 == class2));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
