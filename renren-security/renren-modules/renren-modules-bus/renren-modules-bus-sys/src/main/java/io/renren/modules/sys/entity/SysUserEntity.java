package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
@Data
@TableName("sys_user")
public class SysUserEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
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
     * 密码
     */
    private String sysPasswd;

    /**
     * 是否系统管理员
     */
    private String isSys;
    /**
     * 0待审核 1审核通过 2审核不通过 3 未认证
     */
    private String isAuth;
    /**
     * 是否超期
     */
    private String isOver;
    /**
     * 超期日期
     */
    private Date overTime;
    /**
     * 是否激活
     */
    private String isAct;
    /**
     * 激活日期
     */
    private Date actTime;
    /**
     * 是否锁定
     */
    private String isLocked;
    /**
     * 锁定日期
     */
    private Date lockedTime;
    /**
     * 是否过期凭证
     */
    private String isExpired;
    /**
     * 过期凭证时间
     */
    private Date expiredTime;
    /**
     * 所属员工
     */
    private String staffId;
    /**
     * 服务区
     */
    private String areaId;

}
