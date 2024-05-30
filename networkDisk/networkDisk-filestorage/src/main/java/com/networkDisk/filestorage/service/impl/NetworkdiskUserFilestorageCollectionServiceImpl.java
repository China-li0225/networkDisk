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
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageCollectionBo;
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageCollectionVo;
import com.networkDisk.filestorage.domain.NetworkdiskUserFilestorageCollection;
import com.networkDisk.filestorage.mapper.NetworkdiskUserFilestorageCollectionMapper;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageCollectionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户收藏的文件Service业务层处理
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@RequiredArgsConstructor
@Service
public class NetworkdiskUserFilestorageCollectionServiceImpl implements INetworkdiskUserFilestorageCollectionService {

    private final NetworkdiskUserFilestorageCollectionMapper baseMapper;

    /**
     * 查询用户收藏的文件
     */
    @Override
    public NetworkdiskUserFilestorageCollectionVo queryById(Long collectionId){
        return baseMapper.selectVoById(collectionId);
    }

    /**
     * 查询用户收藏的文件列表
     */
    @Override
    public TableDataInfo<NetworkdiskUserFilestorageCollectionVo> queryPageList(NetworkdiskUserFilestorageCollectionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<NetworkdiskUserFilestorageCollection> lqw = buildQueryWrapper(bo);
        Page<NetworkdiskUserFilestorageCollectionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户收藏的文件列表
     */
    @Override
    public List<NetworkdiskUserFilestorageCollectionVo> queryList(NetworkdiskUserFilestorageCollectionBo bo) {
        LambdaQueryWrapper<NetworkdiskUserFilestorageCollection> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<NetworkdiskUserFilestorageCollection> buildQueryWrapper(NetworkdiskUserFilestorageCollectionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<NetworkdiskUserFilestorageCollection> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFilestorageId() != null, NetworkdiskUserFilestorageCollection::getFilestorageId, bo.getFilestorageId());
        lqw.eq(bo.getUserId() != null, NetworkdiskUserFilestorageCollection::getUserId, bo.getUserId());
        lqw.eq(bo.getOssId() != null, NetworkdiskUserFilestorageCollection::getOssId, bo.getOssId());
        lqw.like(StringUtils.isNotBlank(bo.getFileName()), NetworkdiskUserFilestorageCollection::getFileName, bo.getFileName());
        lqw.like(StringUtils.isNotBlank(bo.getOriginalName()), NetworkdiskUserFilestorageCollection::getOriginalName, bo.getOriginalName());
        lqw.eq(StringUtils.isNotBlank(bo.getFileSuffix()), NetworkdiskUserFilestorageCollection::getFileSuffix, bo.getFileSuffix());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), NetworkdiskUserFilestorageCollection::getUrl, bo.getUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getHistoryType()), NetworkdiskUserFilestorageCollection::getHistoryType, bo.getHistoryType());
        return lqw;
    }

    /**
     * 新增用户收藏的文件
     */
    @Override
    public Boolean insertByBo(NetworkdiskUserFilestorageCollectionBo bo) {
        NetworkdiskUserFilestorageCollection add = BeanUtil.toBean(bo, NetworkdiskUserFilestorageCollection.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCollectionId(add.getCollectionId());
        }
        return flag;
    }

    /**
     * 修改用户收藏的文件
     */
    @Override
    public Boolean updateByBo(NetworkdiskUserFilestorageCollectionBo bo) {
        NetworkdiskUserFilestorageCollection update = BeanUtil.toBean(bo, NetworkdiskUserFilestorageCollection.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(NetworkdiskUserFilestorageCollection entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户收藏的文件
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
