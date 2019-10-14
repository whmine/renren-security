package io.renren.modules.sys.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.renren.common.base.BaseRequest;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Data
@Api("数据字典类型-保存")
public class SysDictDataSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 中文名称
     */
    @ApiModelProperty(value = "中文名称")
    private String cnName;
    /**
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签")
    private String dictLabel;
    /**
     * 字典键值
     */
    @ApiModelProperty(value = "字典键值")
    private String dictValue;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;


}
