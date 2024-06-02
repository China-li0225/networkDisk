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
import com.networkDisk.common.enums.BusinessType;
import com.networkDisk.common.utils.poi.ExcelUtil;
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageCollectionVo;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageCollectionBo;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageCollectionService;
import com.networkDisk.common.core.page.TableDataInfo;

/**
 * 用户收藏的文件
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/filestoragecollection")
public class NetworkdiskUserFilestorageCollectionController extends BaseController {

    private final INetworkdiskUserFilestorageCollectionService iNetworkdiskUserFilestorageCollectionService;

    /**
     * 查询用户收藏的文件列表
     */
    @SaCheckPermission("filestorage:filestoragecollection:list")
    @GetMapping("/list")
    public TableDataInfo<NetworkdiskUserFilestorageCollectionVo> list(NetworkdiskUserFilestorageCollectionBo bo, PageQuery pageQuery) {
        return iNetworkdiskUserFilestorageCollectionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户收藏的文件列表
     */
    @SaCheckPermission("filestorage:filestoragecollection:export")
    @Log(title = "用户收藏的文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(NetworkdiskUserFilestorageCollectionBo bo, HttpServletResponse response) {
        List<NetworkdiskUserFilestorageCollectionVo> list = iNetworkdiskUserFilestorageCollectionService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户收藏的文件", NetworkdiskUserFilestorageCollectionVo.class, response);
    }

    /**
     * 获取用户收藏的文件详细信息
     *
     * @param collectionId 主键
     */
    @SaCheckPermission("filestorage:filestoragecollection:query")
    @GetMapping("/{collectionId}")
    public R<NetworkdiskUserFilestorageCollectionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long collectionId) {
        return R.ok(iNetworkdiskUserFilestorageCollectionService.queryById(collectionId));
    }

    /**
     * 新增用户收藏的文件
     */
    @SaCheckPermission("filestorage:filestoragecollection:add")
    @Log(title = "用户收藏的文件", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody NetworkdiskUserFilestorageCollectionBo bo) {
        return toAjax(iNetworkdiskUserFilestorageCollectionService.insertByBo(bo));
    }

    /**
     * 修改用户收藏的文件
     */
    @SaCheckPermission("filestorage:filestoragecollection:edit")
    @Log(title = "用户收藏的文件", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody NetworkdiskUserFilestorageCollectionBo bo) {
        return toAjax(iNetworkdiskUserFilestorageCollectionService.updateByBo(bo));
    }

    /**
     * 删除用户收藏的文件
     *
     * @param collectionIds 主键串
     */
    @SaCheckPermission("filestorage:filestoragecollection:remove")
    @Log(title = "用户收藏的文件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{collectionIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] collectionIds) {
        return toAjax(iNetworkdiskUserFilestorageCollectionService.deleteWithValidByIds(Arrays.asList(collectionIds), true));
    }
}
