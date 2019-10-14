package io.renren.modules.cu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户统计信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
@Data
@TableName("cu_count")
public class CuCountEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 星级
     */
    private String customerLevel;

}
