package com.networkDisk.filestorage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.networkDisk.common.utils.StringUtils;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageHistoryBo;
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageHistoryVo;
import com.networkDisk.filestorage.domain.NetworkdiskUserFilestorageHistory;
import com.networkDisk.filestorage.mapper.NetworkdiskUserFilestorageHistoryMapper;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageHistoryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户文件历史记录Service业务层处理
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@RequiredArgsConstructor
@Service
public class NetworkdiskUserFilestorageHistoryServiceImpl implements INetworkdiskUserFilestorageHistoryService {

    private final NetworkdiskUserFilestorageHistoryMapper baseMapper;

    /**
     * 查询用户文件历史记录
     */
    @Override
    public NetworkdiskUserFilestorageHistoryVo queryById(Long historyId){
        return baseMapper.selectVoById(historyId);
    }

    /**
     * 查询用户文件历史记录列表
     */
    @Override
    public TableDataInfo<NetworkdiskUserFilestorageHistoryVo> queryPageList(NetworkdiskUserFilestorageHistoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<NetworkdiskUserFilestorageHistory> lqw = buildQueryWrapper(bo);
        Page<NetworkdiskUserFilestorageHistoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户文件历史记录列表
     */
    @Override
    public List<NetworkdiskUserFilestorageHistoryVo> queryList(NetworkdiskUserFilestorageHistoryBo bo) {
        LambdaQueryWrapper<NetworkdiskUserFilestorageHistory> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<NetworkdiskUserFilestorageHistory> buildQueryWrapper(NetworkdiskUserFilestorageHistoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<NetworkdiskUserFilestorageHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFilestorageId() != null, NetworkdiskUserFilestorageHistory::getFilestorageId, bo.getFilestorageId());
        lqw.eq(bo.getUserId() != null, NetworkdiskUserFilestorageHistory::getUserId, bo.getUserId());
        lqw.eq(bo.getOssId() != null, NetworkdiskUserFilestorageHistory::getOssId, bo.getOssId());
        lqw.like(StringUtils.isNotBlank(bo.getFileName()), NetworkdiskUserFilestorageHistory::getFileName, bo.getFileName());
        lqw.like(StringUtils.isNotBlank(bo.getOriginalName()), NetworkdiskUserFilestorageHistory::getOriginalName, bo.getOriginalName());
        lqw.eq(StringUtils.isNotBlank(bo.getFileSuffix()), NetworkdiskUserFilestorageHistory::getFileSuffix, bo.getFileSuffix());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), NetworkdiskUserFilestorageHistory::getUrl, bo.getUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getHistoryType()), NetworkdiskUserFilestorageHistory::getHistoryType, bo.getHistoryType());
        return lqw;
    }

    /**
     * 新增用户文件历史记录
     */
    @Override
    public Boolean insertByBo(NetworkdiskUserFilestorageHistoryBo bo) {
        NetworkdiskUserFilestorageHistory add = BeanUtil.toBean(bo, NetworkdiskUserFilestorageHistory.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setHistoryId(add.getHistoryId());
        }
        return flag;
    }

    /**
     * 修改用户文件历史记录
     */
    @Override
    public Boolean updateByBo(NetworkdiskUserFilestorageHistoryBo bo) {
        NetworkdiskUserFilestorageHistory update = BeanUtil.toBean(bo, NetworkdiskUserFilestorageHistory.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(NetworkdiskUserFilestorageHistory entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户文件历史记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
