package com.networkDisk.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.networkDisk.common.core.domain.BaseEntity;
import lombok.NoArgsConstructor;

/**
 * 用户组信息对象 sys_group
 *
 * @author networkdisk
 * @date 2024-05-29
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_group")
public class SysGroup extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 用户组ID
     */
    @TableId(value = "group_id")
    private Long groupId;
    /**
     * 用户组编码
     */
    private String groupCode;
    /**
     * 用户组名称
     */
    private String groupName;
    /**
     * 显示顺序
     */
    private Long groupSort;
    /**
     * 用户组等级
     */
    private Long groupLevel;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
