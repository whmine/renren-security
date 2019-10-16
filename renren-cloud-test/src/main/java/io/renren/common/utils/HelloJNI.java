package io.renren.common.utils;

public class HelloJNI {

    static {
        // hello.dll (Windows) or libhello.so (Unixes)
        System.load("msvcrt");
    }

    private native static void printf(String format, Object... args);

    public static void main(String[] args) {

        HelloJNI.printf("=====================");  // invoke the native method
    }
}
