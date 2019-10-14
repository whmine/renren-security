package io.renren.modules.sys.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Data
@Api("数据字典-分页查询")
public class SysDictOptionPageRequest extends PageRequest {


    @ApiModelProperty(value = "字典类型")
    private String type;


}
