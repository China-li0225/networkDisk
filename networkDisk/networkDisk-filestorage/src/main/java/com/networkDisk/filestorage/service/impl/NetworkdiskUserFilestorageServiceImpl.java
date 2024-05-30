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
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageBo;
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageVo;
import com.networkDisk.filestorage.domain.NetworkdiskUserFilestorage;
import com.networkDisk.filestorage.mapper.NetworkdiskUserFilestorageMapper;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageService;

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
public class NetworkdiskUserFilestorageServiceImpl implements INetworkdiskUserFilestorageService {

    private final NetworkdiskUserFilestorageMapper baseMapper;

    /**
     * 查询用户文件存储
     */
    @Override
    public NetworkdiskUserFilestorageVo queryById(Long filestorageId){
        return baseMapper.selectVoById(filestorageId);
    }

    /**
     * 查询用户文件存储列表
     */
    @Override
    public TableDataInfo<NetworkdiskUserFilestorageVo> queryPageList(NetworkdiskUserFilestorageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<NetworkdiskUserFilestorage> lqw = buildQueryWrapper(bo);
        Page<NetworkdiskUserFilestorageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户文件存储列表
     */
    @Override
    public List<NetworkdiskUserFilestorageVo> queryList(NetworkdiskUserFilestorageBo bo) {
        LambdaQueryWrapper<NetworkdiskUserFilestorage> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<NetworkdiskUserFilestorage> buildQueryWrapper(NetworkdiskUserFilestorageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<NetworkdiskUserFilestorage> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, NetworkdiskUserFilestorage::getUserId, bo.getUserId());
        lqw.eq(bo.getOssId() != null, NetworkdiskUserFilestorage::getOssId, bo.getOssId());
        lqw.like(StringUtils.isNotBlank(bo.getFileName()), NetworkdiskUserFilestorage::getFileName, bo.getFileName());
        lqw.like(StringUtils.isNotBlank(bo.getOriginalName()), NetworkdiskUserFilestorage::getOriginalName, bo.getOriginalName());
        lqw.eq(StringUtils.isNotBlank(bo.getFileSuffix()), NetworkdiskUserFilestorage::getFileSuffix, bo.getFileSuffix());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), NetworkdiskUserFilestorage::getUrl, bo.getUrl());
        return lqw;
    }

    /**
     * 新增用户文件存储
     */
    @Override
    public Boolean insertByBo(NetworkdiskUserFilestorageBo bo) {
        NetworkdiskUserFilestorage add = BeanUtil.toBean(bo, NetworkdiskUserFilestorage.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setFilestorageId(add.getFilestorageId());
        }
        return flag;
    }

    /**
     * 修改用户文件存储
     */
    @Override
    public Boolean updateByBo(NetworkdiskUserFilestorageBo bo) {
        NetworkdiskUserFilestorage update = BeanUtil.toBean(bo, NetworkdiskUserFilestorage.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(NetworkdiskUserFilestorage entity){
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
