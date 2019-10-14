package io.renren.modules.oauth.request;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.IDRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
        import java.math.BigDecimal;
    import java.io.Serializable;
import java.util.Date;

/**
 * 授权TOKEN
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 09:23:04
 */
@Data
@Api("授权TOKEN-删除")
public class OauthTokenDelRequest extends IDRequest {

}
