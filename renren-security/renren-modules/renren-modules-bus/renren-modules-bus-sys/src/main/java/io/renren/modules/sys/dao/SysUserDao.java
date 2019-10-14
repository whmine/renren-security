package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */

@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

}
