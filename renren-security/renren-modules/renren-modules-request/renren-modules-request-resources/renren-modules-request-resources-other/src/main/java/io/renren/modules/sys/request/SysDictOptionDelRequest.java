package io.renren.modules.sys.request;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.IDRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
@Api("数据字典-删除")
public class SysDictOptionDelRequest extends IDRequest {

}
