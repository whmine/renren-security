package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysAuthoritiesEntity;
import io.renren.modules.sys.entity.SysRoleAuthoritiesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统角色权限关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */

@Mapper
public interface SysRoleAuthoritiesDao extends BaseMapper<SysRoleAuthoritiesEntity> {

    List<SysAuthoritiesEntity> rightList(Map map);

    List<SysAuthoritiesEntity> leftList(Map map);

}
