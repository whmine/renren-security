package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统文件管理
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 14:34:35
 */
@Data
@TableName("sys_attachment")
public class SysAttachmentEntity extends BaseEntity {
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
     * 类型
     */
    private String type;
    /**
     * 加密类型
     */
    private String sysType;
    /**
     * 内容
     */
    private String urlPath;
    /**
     * 大小(KB)
     */
    private String length;

    private String sysUserId;

}
