package com.networkDisk.system.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.networkDisk.common.annotation.RepeatSubmit;
import com.networkDisk.common.annotation.Log;
import com.networkDisk.common.core.controller.BaseController;
import com.networkDisk.common.core.domain.PageQuery;
import com.networkDisk.common.core.domain.R;
import com.networkDisk.common.core.validate.AddGroup;
import com.networkDisk.common.core.validate.EditGroup;
import com.networkDisk.common.core.validate.QueryGroup;
import com.networkDisk.common.enums.BusinessType;
import com.networkDisk.common.utils.poi.ExcelUtil;
import com.networkDisk.system.domain.vo.SysGroupVo;
import com.networkDisk.system.domain.bo.SysGroupBo;
import com.networkDisk.system.service.ISysGroupService;
import com.networkDisk.common.core.page.TableDataInfo;

/**
 * 用户组信息
 *
 * @author networkdisk
 * @date 2024-05-29
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/group")
public class SysGroupController extends BaseController {

    private final ISysGroupService iSysGroupService;

    /**
     * 查询用户组信息列表
     */
    @SaCheckPermission("system:group:list")
    @GetMapping("/list")
    public TableDataInfo<SysGroupVo> list(SysGroupBo bo, PageQuery pageQuery) {
        return iSysGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户组信息列表
     */
    @SaCheckPermission("system:group:export")
    @Log(title = "用户组信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysGroupBo bo, HttpServletResponse response) {
        List<SysGroupVo> list = iSysGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户组信息", SysGroupVo.class, response);
    }

    /**
     * 获取用户组信息详细信息
     *
     * @param groupId 主键
     */
    @SaCheckPermission("system:group:query")
    @GetMapping("/{groupId}")
    public R<SysGroupVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long groupId) {
        return R.ok(iSysGroupService.queryById(groupId));
    }

    /**
     * 新增用户组信息
     */
    @SaCheckPermission("system:group:add")
    @Log(title = "用户组信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysGroupBo bo) {
        return toAjax(iSysGroupService.insertByBo(bo));
    }

    /**
     * 修改用户组信息
     */
    @SaCheckPermission("system:group:edit")
    @Log(title = "用户组信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysGroupBo bo) {
        return toAjax(iSysGroupService.updateByBo(bo));
    }
    /**
     * 修改用户组状态
     */
    @SaCheckPermission("system:group:edit")
    @Log(title = "用户组信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@Validated(EditGroup.class) @RequestBody SysGroupBo bo) {
        return toAjax(iSysGroupService.changeStatusBo(bo));
    }

    /**
     * 删除用户组信息
     *
     * @param groupIds 主键串
     */
    @SaCheckPermission("system:group:remove")
    @Log(title = "用户组信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{groupIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] groupIds) {
        return toAjax(iSysGroupService.deleteWithValidByIds(Arrays.asList(groupIds), true));
    }
}
