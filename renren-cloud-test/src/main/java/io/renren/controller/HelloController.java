package io.renren.controller;

import com.sun.jna.ptr.IntByReference;
import io.renren.common.utils.AppUtils;
import io.renren.common.utils.CLibrary;
import io.renren.common.utils.CLibrary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author: @我没有三颗心脏
 * @create: 2018-05-08-下午 16:46
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {

        IntByReference cnt = new IntByReference(0);
        int key = 1904290002;
        int sn = 1904290001;
        byte[] pass = new byte[6];
        System.out.println("===========2==================================");
//        CLibrary.INSTANCE.zdmmxencrypt(cnt, key, sn, 2, pass);
//        AppUtils.zdmmxencrypt(cnt, key, sn, 2, pass);
//        System.out.println("===========3==================================" + AppUtils.add(1, 2));
//        CLibrary.INSTANCE.printf("hello=================");
        String str = "";
        for (int i = 0; i < pass.length; i++) {
            str += "|" + pass[i];
        }
        return str;
    }
}
