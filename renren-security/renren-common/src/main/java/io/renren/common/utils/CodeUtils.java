package io.renren.common.utils;

import java.util.UUID;

/***
 * 编码工具
 */
public class CodeUtils {

    public static String generateID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
