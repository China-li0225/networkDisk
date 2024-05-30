package com.networkDisk.system.domain.bo;

import com.networkDisk.common.core.validate.AddGroup;
import com.networkDisk.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.networkDisk.common.core.domain.BaseEntity;

/**
 * 用户组信息业务对象 sys_group
 *
 * @author networkdisk
 * @date 2024-05-29
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysGroupBo extends BaseEntity {

    /**
     * 用户组ID
     */
    @NotNull(message = "用户组ID不能为空", groups = { EditGroup.class })
    private Long groupId;

    /**
     * 用户组编码
     */
    @NotBlank(message = "用户组编码不能为空", groups = { AddGroup.class})
    private String groupCode;

    /**
     * 用户组名称
     */
    @NotBlank(message = "用户组名称不能为空", groups = { AddGroup.class})
    private String groupName;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { AddGroup.class})
    private Long groupSort;

    /**
     * 用户组等级
     */
    @NotNull(message = "用户组等级不能为空", groups = { AddGroup.class})
    private Long groupLevel;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class })
    private String status;

    /**
     * 备注
     */
    private String remark;


}
