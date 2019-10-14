package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统菜单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */

@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

}
