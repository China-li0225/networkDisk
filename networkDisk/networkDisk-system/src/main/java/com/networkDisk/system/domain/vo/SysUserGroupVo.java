package com.networkDisk.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.networkDisk.common.annotation.ExcelDictFormat;
import com.networkDisk.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 用户与用户组关联视图对象 sys_user_group
 *
 * @author networkdisk
 * @date 2024-05-29
 */
@Data
@ExcelIgnoreUnannotated
public class SysUserGroupVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 岗位ID
     */
    @ExcelProperty(value = "岗位ID")
    private Long groupId;


}
