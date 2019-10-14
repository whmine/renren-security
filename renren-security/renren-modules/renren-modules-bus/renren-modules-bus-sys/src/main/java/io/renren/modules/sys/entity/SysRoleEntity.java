package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
@Data
@TableName("sys_role")
public class SysRoleEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 中文名称
     */
    private String cnName;
    /**
     * 是否系统角色
     */
    private String isSys;
    /**
     * 层级
     */
    private BigDecimal level;
    /**
     * 可否继承
     */
    private String inheritable;
    /**
     * 继承角色
     */
    private String parentRole;

}
