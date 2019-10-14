import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public interface CLibrary extends Library {
    // DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径。（此处：(Platform.isWindows()?"msvcrt":"c")指本地动态库msvcrt.dll）
    CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "D:\\libzdmmxencrypt" : "\\data\\so\\libzdmmxencrypt"), CLibrary.class);

    // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)
//    void printf(String format, Object... args);
    void zdmmxencrypt(String runnum, String key, String sn, String usertime, Object... args);
}
