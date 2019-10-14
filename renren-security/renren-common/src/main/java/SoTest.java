public class SoTest {

    public static void main(String[] args) {
        String cnt = "0";
        String key = "2718793814";
        String sn = "1904290001";
        String[] pass = new String[6];
        CLibrary.INSTANCE.zdmmxencrypt(cnt, key, sn, "2", pass);
        for (int i = 0; i < pass.length; i++) {
            System.out.println("返回结果集[" + i + "]" + pass[i]);
        }
    }
}
