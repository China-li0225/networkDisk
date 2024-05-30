package com.networkDisk.filestorage.controller;

import java.util.List;
import java.util.Arrays;

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
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageVo;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageBo;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageService;
import com.networkDisk.common.core.page.TableDataInfo;

/**
 * 用户文件存储
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/filestorage")
public class NetworkdiskUserFilestorageController extends BaseController {

    private final INetworkdiskUserFilestorageService iNetworkdiskUserFilestorageService;

    /**
     * 查询用户文件存储列表
     */
    @SaCheckPermission("filestorage:filestorage:list")
    @GetMapping("/list")
    public TableDataInfo<NetworkdiskUserFilestorageVo> list(NetworkdiskUserFilestorageBo bo, PageQuery pageQuery) {
        return iNetworkdiskUserFilestorageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户文件存储列表
     */
    @SaCheckPermission("filestorage:filestorage:export")
    @Log(title = "用户文件存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(NetworkdiskUserFilestorageBo bo, HttpServletResponse response) {
        List<NetworkdiskUserFilestorageVo> list = iNetworkdiskUserFilestorageService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户文件存储", NetworkdiskUserFilestorageVo.class, response);
    }

    /**
     * 获取用户文件存储详细信息
     *
     * @param filestorageId 主键
     */
    @SaCheckPermission("filestorage:filestorage:query")
    @GetMapping("/{filestorageId}")
    public R<NetworkdiskUserFilestorageVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long filestorageId) {
        return R.ok(iNetworkdiskUserFilestorageService.queryById(filestorageId));
    }

    /**
     * 新增用户文件存储
     */
    @SaCheckPermission("filestorage:filestorage:add")
    @Log(title = "用户文件存储", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody NetworkdiskUserFilestorageBo bo) {
        return toAjax(iNetworkdiskUserFilestorageService.insertByBo(bo));
    }

    /**
     * 修改用户文件存储
     */
    @SaCheckPermission("filestorage:filestorage:edit")
    @Log(title = "用户文件存储", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody NetworkdiskUserFilestorageBo bo) {
        return toAjax(iNetworkdiskUserFilestorageService.updateByBo(bo));
    }

    /**
     * 删除用户文件存储
     *
     * @param filestorageIds 主键串
     */
    @SaCheckPermission("filestorage:filestorage:remove")
    @Log(title = "用户文件存储", businessType = BusinessType.DELETE)
    @DeleteMapping("/{filestorageIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] filestorageIds) {
        return toAjax(iNetworkdiskUserFilestorageService.deleteWithValidByIds(Arrays.asList(filestorageIds), true));
    }
}
