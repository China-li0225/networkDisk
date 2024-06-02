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
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageRecyclebinVo;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageRecyclebinBo;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageRecyclebinService;
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
@RequestMapping("/userFilestorageRecyclebin")
public class NetworkdiskUserFilestorageRecyclebinController extends BaseController {

    private final INetworkdiskUserFilestorageRecyclebinService iNetworkdiskUserFilestorageRecyclebinService;

    /**
     * 查询用户文件存储列表
     */
    @SaCheckPermission("filestorage:userFilestorageRecyclebin:list")
    @GetMapping("/list")
    public TableDataInfo<NetworkdiskUserFilestorageRecyclebinVo> list(NetworkdiskUserFilestorageRecyclebinBo bo, PageQuery pageQuery) {
        return iNetworkdiskUserFilestorageRecyclebinService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户文件存储列表
     */
    @SaCheckPermission("filestorage:userFilestorageRecyclebin:export")
    @Log(title = "用户文件存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(NetworkdiskUserFilestorageRecyclebinBo bo, HttpServletResponse response) {
        List<NetworkdiskUserFilestorageRecyclebinVo> list = iNetworkdiskUserFilestorageRecyclebinService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户文件存储", NetworkdiskUserFilestorageRecyclebinVo.class, response);
    }

    /**
     * 获取用户文件存储详细信息
     *
     * @param expirationId 主键
     */
    @SaCheckPermission("filestorage:userFilestorageRecyclebin:query")
    @GetMapping("/{expirationId}")
    public R<NetworkdiskUserFilestorageRecyclebinVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long expirationId) {
        return R.ok(iNetworkdiskUserFilestorageRecyclebinService.queryById(expirationId));
    }

    /**
     * 新增用户文件存储
     */
    @SaCheckPermission("filestorage:userFilestorageRecyclebin:add")
    @Log(title = "用户文件存储", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody NetworkdiskUserFilestorageRecyclebinBo bo) {
        return toAjax(iNetworkdiskUserFilestorageRecyclebinService.insertByBo(bo));
    }

    /**
     * 修改用户文件存储
     */
    @SaCheckPermission("filestorage:userFilestorageRecyclebin:edit")
    @Log(title = "用户文件存储", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody NetworkdiskUserFilestorageRecyclebinBo bo) {
        return toAjax(iNetworkdiskUserFilestorageRecyclebinService.updateByBo(bo));
    }

    /**
     * 从回收站将文件移动到用户文件列表
     *
     * @param expirationIds 主键串
     */
    @SaCheckPermission("filestorage:userFilestorageRecyclebin:remove")
    @Log(title = "用户文件存储", businessType = BusinessType.DELETE)
    @DeleteMapping("/{expirationIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] expirationIds) {

        return toAjax(iNetworkdiskUserFilestorageRecyclebinService.removeToFilestorage(expirationIds));
    }

    /**
     * 删除用户文件存储
     *
     * @param expirationIds 主键串
     */
    @SaCheckPermission("filestorage:userFilestorageRecyclebin:remove")
    @Log(title = "用户文件存储", businessType = BusinessType.DELETE)
    @DeleteMapping("delete/{expirationIds}")
    public R<Void> delete(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] expirationIds) {
        return toAjax(iNetworkdiskUserFilestorageRecyclebinService.deleteWithValidByIds(Arrays.asList(expirationIds), true));
    }
}
