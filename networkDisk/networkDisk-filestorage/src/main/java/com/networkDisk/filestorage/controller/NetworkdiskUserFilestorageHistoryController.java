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
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageHistoryVo;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageHistoryBo;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageHistoryService;
import com.networkDisk.common.core.page.TableDataInfo;

/**
 * 用户文件历史记录
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/userFilestorageHistory")
public class NetworkdiskUserFilestorageHistoryController extends BaseController {

    private final INetworkdiskUserFilestorageHistoryService iNetworkdiskUserFilestorageHistoryService;

    /**
     * 查询用户文件历史记录列表
     */
    @SaCheckPermission("filestorage:userFilestorageHistory:list")
    @GetMapping("/list")
    public TableDataInfo<NetworkdiskUserFilestorageHistoryVo> list(NetworkdiskUserFilestorageHistoryBo bo, PageQuery pageQuery) {
        return iNetworkdiskUserFilestorageHistoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户文件历史记录列表
     */
    @SaCheckPermission("filestorage:userFilestorageHistory:export")
    @Log(title = "用户文件历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(NetworkdiskUserFilestorageHistoryBo bo, HttpServletResponse response) {
        List<NetworkdiskUserFilestorageHistoryVo> list = iNetworkdiskUserFilestorageHistoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户文件历史记录", NetworkdiskUserFilestorageHistoryVo.class, response);
    }

    /**
     * 获取用户文件历史记录详细信息
     *
     * @param historyId 主键
     */
    @SaCheckPermission("filestorage:userFilestorageHistory:query")
    @GetMapping("/{historyId}")
    public R<NetworkdiskUserFilestorageHistoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long historyId) {
        return R.ok(iNetworkdiskUserFilestorageHistoryService.queryById(historyId));
    }

    /**
     * 新增用户文件历史记录
     */
    @SaCheckPermission("filestorage:userFilestorageHistory:add")
    @Log(title = "用户文件历史记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody NetworkdiskUserFilestorageHistoryBo bo) {
        return toAjax(iNetworkdiskUserFilestorageHistoryService.insertByBo(bo));
    }

    /**
     * 修改用户文件历史记录
     */
    @SaCheckPermission("filestorage:userFilestorageHistory:edit")
    @Log(title = "用户文件历史记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody NetworkdiskUserFilestorageHistoryBo bo) {
        return toAjax(iNetworkdiskUserFilestorageHistoryService.updateByBo(bo));
    }

    /**
     * 删除用户文件历史记录
     *
     * @param historyIds 主键串
     */
    @SaCheckPermission("filestorage:userFilestorageHistory:remove")
    @Log(title = "用户文件历史记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{historyIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] historyIds) {
        return toAjax(iNetworkdiskUserFilestorageHistoryService.deleteWithValidByIds(Arrays.asList(historyIds), true));
    }
}
