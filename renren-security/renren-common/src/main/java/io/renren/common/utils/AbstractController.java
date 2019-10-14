/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import io.renren.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Controller公共组件
 *
 * @author Mark sunlightcs@gmail.com
 */

public abstract class AbstractController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String generateID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 短信验证码校验证
     *
     * @param phone
     * @param type
     * @param codeNum
     * @return
     */
    private boolean checkSms(String phone, Integer type, String codeNum) {

        String code = stringRedisTemplate.opsForValue().get(phone + Constants.STATE_YES + type);
        if (codeNum.equals(code)) {
            stringRedisTemplate.delete(phone + Constants.STATE_YES + type);
            return true;
        }
        return false;
    }
}
