package io.renren.modules.cu.request;
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
 * 客户统计信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
@Data
@Api("客户统计信息-保存")
public class CuCountSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 星级
 */
                @ApiModelProperty(value = "星级")
private String customerLevel;
                                                                                                                                                                                                

}
