package com.networkDisk.web.controller.system;

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
import com.networkDisk.system.domain.vo.SysUserGroupVo;
import com.networkDisk.system.domain.bo.SysUserGroupBo;
import com.networkDisk.system.service.ISysUserGroupService;
import com.networkDisk.common.core.page.TableDataInfo;

/**
 * 用户与用户组关联
 *
 * @author networkdisk
 * @date 2024-05-29
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/userGroup")
public class SysUserGroupController extends BaseController {

    private final ISysUserGroupService iSysUserGroupService;

    /**
     * 查询用户与用户组关联列表
     */
    @SaCheckPermission("system:userGroup:list")
    @GetMapping("/list")
    public TableDataInfo<SysUserGroupVo> list(SysUserGroupBo bo, PageQuery pageQuery) {
        return iSysUserGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户与用户组关联列表
     */
    @SaCheckPermission("system:userGroup:export")
    @Log(title = "用户与用户组关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysUserGroupBo bo, HttpServletResponse response) {
        List<SysUserGroupVo> list = iSysUserGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户与用户组关联", SysUserGroupVo.class, response);
    }

    /**
     * 获取用户与用户组关联详细信息
     *
     * @param userId 主键
     */
    @SaCheckPermission("system:userGroup:query")
    @GetMapping("/{userId}")
    public R<SysUserGroupVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long userId) {
        return R.ok(iSysUserGroupService.queryById(userId));
    }

    /**
     * 新增用户与用户组关联
     */
    @SaCheckPermission("system:userGroup:add")
    @Log(title = "用户与用户组关联", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysUserGroupBo bo) {
        return toAjax(iSysUserGroupService.insertByBo(bo));
    }

    /**
     * 修改用户与用户组关联
     */
    @SaCheckPermission("system:userGroup:edit")
    @Log(title = "用户与用户组关联", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysUserGroupBo bo) {
        return toAjax(iSysUserGroupService.updateByBo(bo));
    }

    /**
     * 删除用户与用户组关联
     *
     * @param userIds 主键串
     */
    @SaCheckPermission("system:userGroup:remove")
    @Log(title = "用户与用户组关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] userIds) {
        return toAjax(iSysUserGroupService.deleteWithValidByIds(Arrays.asList(userIds), true));
    }
}
