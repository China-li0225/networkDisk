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
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageRecyclebinBo;
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageRecyclebinVo;
import com.networkDisk.filestorage.domain.NetworkdiskUserFilestorageRecyclebin;
import com.networkDisk.filestorage.mapper.NetworkdiskUserFilestorageRecyclebinMapper;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageRecyclebinService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户文件存储Service业务层处理
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@RequiredArgsConstructor
@Service
public class NetworkdiskUserFilestorageRecyclebinServiceImpl implements INetworkdiskUserFilestorageRecyclebinService {

    private final NetworkdiskUserFilestorageRecyclebinMapper baseMapper;

    /**
     * 查询用户文件存储
     */
    @Override
    public NetworkdiskUserFilestorageRecyclebinVo queryById(Long expirationId){
        return baseMapper.selectVoById(expirationId);
    }

    /**
     * 查询用户文件存储列表
     */
    @Override
    public TableDataInfo<NetworkdiskUserFilestorageRecyclebinVo> queryPageList(NetworkdiskUserFilestorageRecyclebinBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<NetworkdiskUserFilestorageRecyclebin> lqw = buildQueryWrapper(bo);
        Page<NetworkdiskUserFilestorageRecyclebinVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户文件存储列表
     */
    @Override
    public List<NetworkdiskUserFilestorageRecyclebinVo> queryList(NetworkdiskUserFilestorageRecyclebinBo bo) {
        LambdaQueryWrapper<NetworkdiskUserFilestorageRecyclebin> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<NetworkdiskUserFilestorageRecyclebin> buildQueryWrapper(NetworkdiskUserFilestorageRecyclebinBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<NetworkdiskUserFilestorageRecyclebin> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFilestorageId() != null, NetworkdiskUserFilestorageRecyclebin::getFilestorageId, bo.getFilestorageId());
        lqw.eq(bo.getUserId() != null, NetworkdiskUserFilestorageRecyclebin::getUserId, bo.getUserId());
        lqw.eq(bo.getOssId() != null, NetworkdiskUserFilestorageRecyclebin::getOssId, bo.getOssId());
        lqw.like(StringUtils.isNotBlank(bo.getFileName()), NetworkdiskUserFilestorageRecyclebin::getFileName, bo.getFileName());
        lqw.like(StringUtils.isNotBlank(bo.getOriginalName()), NetworkdiskUserFilestorageRecyclebin::getOriginalName, bo.getOriginalName());
        lqw.eq(StringUtils.isNotBlank(bo.getFileSuffix()), NetworkdiskUserFilestorageRecyclebin::getFileSuffix, bo.getFileSuffix());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), NetworkdiskUserFilestorageRecyclebin::getUrl, bo.getUrl());
        lqw.eq(bo.getFileExpirationDate() != null, NetworkdiskUserFilestorageRecyclebin::getFileExpirationDate, bo.getFileExpirationDate());
        return lqw;
    }

    /**
     * 新增用户文件存储
     */
    @Override
    public Boolean insertByBo(NetworkdiskUserFilestorageRecyclebinBo bo) {
        NetworkdiskUserFilestorageRecyclebin add = BeanUtil.toBean(bo, NetworkdiskUserFilestorageRecyclebin.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setExpirationId(add.getExpirationId());
        }
        return flag;
    }

    /**
     * 修改用户文件存储
     */
    @Override
    public Boolean updateByBo(NetworkdiskUserFilestorageRecyclebinBo bo) {
        NetworkdiskUserFilestorageRecyclebin update = BeanUtil.toBean(bo, NetworkdiskUserFilestorageRecyclebin.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(NetworkdiskUserFilestorageRecyclebin entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户文件存储
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
