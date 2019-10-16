package io.renren.common.utils;

//import com.sun.jna.ptr.IntByReference;

//public class HelloMain {
//
//    public static void main(String[] args) {
//        IntByReference cnt = new IntByReference(0);
//        int key = 1904290002;
//        int sn = 1904290001;
//        byte[] pass = new byte[6];
////        System.out.println("===========2=================================="+CLibrary.class.getResource("/libzdmmxencrypt.so").getPath().substring(1));
////        CLibrary.INSTANCE.zdmmxencrypt(cnt, key, sn, 2, pass);
////        System.out.println(CLibrary.INSTANCE.addtest(1, 1));
////        AppUtils.printf("hello===");
////        CLibrary.INSTANCE.test();
////        System.out.println(AppUtils.add(1, 1));
////        System.out.println(CLibrary.INSTANCE.add(1, 1));
//        CLibrary.INSTANCE.printf("hello=================");
//    }
//}

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class HelloMain {
    //继承Library，用于加载库文件
    public interface Clibrary extends Library {
        //加载libhello.so链接库
        HelloMain.Clibrary INSTANTCE = (HelloMain.Clibrary) Native.loadLibrary((Platform.isWindows() ? "D:\\work\\091001\\renren-security20191014\\renren-cloud-test\\test" : "data\\jar\\libtest1"), HelloMain.Clibrary.class);

        //此方法为链接库中的方法
        int add(int a, int b);

        int substract(int a, int b);

        void printHello();
    }

    public static void main(String[] args) {
        //调用
        int sum = HelloMain.Clibrary.INSTANTCE.add(5, 3);
        int sub = HelloMain.Clibrary.INSTANTCE.substract(5, 3);
        System.out.println("add(5,3) = " + sum);
        System.out.println("substract(5,3) = " + sub);
        HelloMain.Clibrary.INSTANTCE.printHello();

    }
}
