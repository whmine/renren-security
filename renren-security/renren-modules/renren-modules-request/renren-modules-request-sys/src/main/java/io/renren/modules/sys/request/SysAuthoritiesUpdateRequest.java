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
 * 系统权限
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统权限-修改")
public class SysAuthoritiesUpdateRequest extends IDRequest {
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
             * SPRING权限名称
             */
                    @ApiModelProperty(value = "SPRING权限名称")
    private String authorities;
                                /**
             * 是否系统权限
             */
                    @ApiModelProperty(value = "是否系统权限")
    private String isSys;
                                                                                                                                                                                                
}
