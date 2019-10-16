package io.renren.common.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.ptr.IntByReference;

public interface CLibrary extends Library {
//    String filePath = CLibrary.class.getResource("").getPath()
//            .replaceFirst("/", "").replaceAll("%20", " ") + "msvcrt.dll";
    // DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径。（此处：(Platform.isWindows()?"msvcrt":"c")指本地动态库msvcrt.dll）
//    CLibrary INSTANCE = (CLibrary) Native.loadLibrary(filePath, CLibrary.class);

    //        CLibrary INSTANCE = (CLibrary) Native.loadLibrary("libzdmmxencrypt", CLibrary.class);
//    CLibrary INSTANCE = (CLibrary) Native.loadLibrary("D:\\test1015", CLibrary.class);

        CLibrary INSTANCE = (CLibrary) Native.loadLibrary("D:\\msvcrt", CLibrary.class);
    // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)
    void printf(String format, Object... args);
//    void zdmmxencrypt(IntByReference runnum, long key, long sn, long usertime, byte[] secret);
//    int addtest(int a, int b);
//此方法为链接库中的方法
//    void test();
}
