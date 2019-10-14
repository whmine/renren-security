package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysResourcesEntity;
import io.renren.modules.sys.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统角色
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */

@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /***
     * 查询用户所属所有角色
     * @param map
     * @return
     */
    List<SysRoleEntity> sysRoleCacheManager(Map map);

    /***
     * 查询角色资源信息
     * @param map
     * @return
     */
    List<SysResourcesEntity> sysRoleResourcesCacheManager(Map map);

}
