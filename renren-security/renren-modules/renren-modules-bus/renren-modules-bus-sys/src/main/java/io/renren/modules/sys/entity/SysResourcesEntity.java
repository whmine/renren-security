package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统资源
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
@Data
@TableName("sys_resources")
public class SysResourcesEntity extends BaseEntity {
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
     * 资源值
     */
    private String value;
    /**
     * 资源类型
     */
    private String type;
    /**
     * 是否系统资源
     */
    private String isSys;

}
