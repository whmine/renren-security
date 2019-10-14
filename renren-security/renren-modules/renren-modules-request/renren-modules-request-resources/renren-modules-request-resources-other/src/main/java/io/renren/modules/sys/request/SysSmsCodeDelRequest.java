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
 * 短信验证码
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:12:24
 */
@Data
@Api("短信验证码-删除")
public class SysSmsCodeDelRequest extends IDRequest {

}
