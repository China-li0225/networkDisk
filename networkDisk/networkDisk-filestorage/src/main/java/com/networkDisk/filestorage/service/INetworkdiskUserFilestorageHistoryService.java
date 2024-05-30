package com.networkDisk.filestorage.service;

import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageHistoryVo;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageHistoryBo;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户文件历史记录Service接口
 *
 * @author networkdisk
 * @date 2024-05-30
 */
public interface INetworkdiskUserFilestorageHistoryService {

    /**
     * 查询用户文件历史记录
     */
    NetworkdiskUserFilestorageHistoryVo queryById(Long historyId);

    /**
     * 查询用户文件历史记录列表
     */
    TableDataInfo<NetworkdiskUserFilestorageHistoryVo> queryPageList(NetworkdiskUserFilestorageHistoryBo bo, PageQuery pageQuery);

    /**
     * 查询用户文件历史记录列表
     */
    List<NetworkdiskUserFilestorageHistoryVo> queryList(NetworkdiskUserFilestorageHistoryBo bo);

    /**
     * 新增用户文件历史记录
     */
    Boolean insertByBo(NetworkdiskUserFilestorageHistoryBo bo);

    /**
     * 修改用户文件历史记录
     */
    Boolean updateByBo(NetworkdiskUserFilestorageHistoryBo bo);

    /**
     * 校验并批量删除用户文件历史记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
