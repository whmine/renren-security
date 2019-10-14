package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色权限关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
@Data
@TableName("sys_role_authorities")
public class SysRoleAuthoritiesEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 系统权限
     */
    private String sysAuthoritiesId;
    /**
     * 系统角色
     */
    private String sysRoleId;

}
