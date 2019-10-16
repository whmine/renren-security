package io.renren.common.utils;

import com.sun.jna.ptr.IntByReference;

public class AppUtils {

    static {
        System.load("D:\\msvcrt.dll");
    }

//    public native static void zdmmxencrypt(IntByReference runnum, int key, int sn, int usertime, byte[] secret);

//    public native static int add(int a, int b);

    public native static void printf(String format, Object... args);
}
