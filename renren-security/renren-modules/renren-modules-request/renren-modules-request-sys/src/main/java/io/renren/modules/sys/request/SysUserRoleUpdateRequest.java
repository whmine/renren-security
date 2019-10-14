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
 * 系统用户角色关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统用户角色关系-修改")
public class SysUserRoleUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 系统角色
             */
                    @ApiModelProperty(value = "系统角色")
    private String sysRoleId;
                                /**
             * 系统用户
             */
                    @ApiModelProperty(value = "系统用户")
    private String sysUserId;
                                                                                                                                                                                                
}
