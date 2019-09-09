package com.xhb.classloader.demo;

// A network ClassLoader
public class NetWorkClassLoader extends ClassLoader {
    private String host;
    private int port;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        final byte[] bytes = loadClassData(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) {
        return null;
    }
}
