package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统服务
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-20 10:12:44
 */
@Data
@TableName("sys_service")
public class SysServiceEntity extends BaseEntity {
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
     * 图标
     */
    private String icon;
    /**
     * 描述
     */
    private String content;
    /**
     * 授权码
     */
    private String authzCode;
    /**
     * 参数
     */
    private String perms;
    /**
     * 是否显示
     */
    private String isShow;
    /**
     * 启动类型
     */
    private String startUpType;
    /**
     * 服务状态
     */
    private String state;
    /**
     * 方法和类名
     */
    private String method;

}
