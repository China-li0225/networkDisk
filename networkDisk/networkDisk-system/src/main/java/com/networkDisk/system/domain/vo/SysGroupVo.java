package com.networkDisk.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.networkDisk.common.annotation.ExcelDictFormat;
import com.networkDisk.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 用户组信息视图对象 sys_group
 *
 * @author networkdisk
 * @date 2024-05-29
 */
@Data
@ExcelIgnoreUnannotated
public class SysGroupVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组ID
     */
    @ExcelProperty(value = "用户组ID")
    private Long groupId;

    /**
     * 用户组编码
     */
    @ExcelProperty(value = "用户组编码")
    private String groupCode;

    /**
     * 用户组名称
     */
    @ExcelProperty(value = "用户组名称")
    private String groupName;

    /**
     * 显示顺序
     */
    @ExcelProperty(value = "显示顺序")
    private Long groupSort;

    /**
     * 用户组等级
     */
    @ExcelProperty(value = "用户组等级")
    private Long groupLevel;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
