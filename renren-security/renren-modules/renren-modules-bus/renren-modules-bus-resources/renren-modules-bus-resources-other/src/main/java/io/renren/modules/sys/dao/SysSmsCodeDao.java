package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysSmsCodeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信验证码
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:12:24
 */

@Mapper
public interface SysSmsCodeDao extends BaseMapper<SysSmsCodeEntity> {

}
