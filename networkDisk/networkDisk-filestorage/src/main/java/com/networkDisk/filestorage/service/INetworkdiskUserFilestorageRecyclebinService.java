package com.networkDisk.filestorage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.networkDisk.filestorage.domain.NetworkdiskUserFilestorageRecyclebin;
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageRecyclebinVo;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageRecyclebinBo;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户文件存储Service接口
 *
 * @author networkdisk
 * @date 2024-05-30
 */
public interface INetworkdiskUserFilestorageRecyclebinService {

    /**
     * 查询用户文件存储
     */
    NetworkdiskUserFilestorageRecyclebinVo queryById(Long expirationId);

    /**
     * 查询用户文件存储列表
     */
    TableDataInfo<NetworkdiskUserFilestorageRecyclebinVo> queryPageList(NetworkdiskUserFilestorageRecyclebinBo bo, PageQuery pageQuery);

    /**
     * 查询用户文件存储列表
     */
    List<NetworkdiskUserFilestorageRecyclebinVo> queryList(NetworkdiskUserFilestorageRecyclebinBo bo);

    /**
     * 新增用户文件存储
     */
    Boolean insertByBo(NetworkdiskUserFilestorageRecyclebinBo bo);

    /**
     * 修改用户文件存储
     */
    Boolean updateByBo(NetworkdiskUserFilestorageRecyclebinBo bo);

    /**
     * 校验并批量删除用户文件存储信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<NetworkdiskUserFilestorageRecyclebin> queryByuserId(Long userId);

    Boolean deleteByWrapper(LambdaQueryWrapper<NetworkdiskUserFilestorageRecyclebin> wrapper);

    boolean removeToFilestorage(Long[] expirationIds);
}
