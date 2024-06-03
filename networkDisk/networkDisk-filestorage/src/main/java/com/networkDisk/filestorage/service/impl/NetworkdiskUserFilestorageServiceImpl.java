package com.networkDisk.filestorage.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.google.api.client.util.SecurityUtils;
import com.networkDisk.common.core.domain.entity.SysUser;
import com.networkDisk.common.utils.StringUtils;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.networkDisk.filestorage.domain.NetworkdiskUserFilestorageRecyclebin;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageRecyclebinBo;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageRecyclebinService;
import com.networkDisk.system.service.ISysOssService;
import com.networkDisk.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageBo;
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageVo;
import com.networkDisk.filestorage.domain.NetworkdiskUserFilestorage;
import com.networkDisk.filestorage.mapper.NetworkdiskUserFilestorageMapper;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
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
    private final ISysUserService iSysUserService;
    private final ISysOssService iSysOssService;
    private final INetworkdiskUserFilestorageRecyclebinService iNetworkdiskUserFilestorageRecyclebinService;

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
        add.setUserId(iSysUserService.getSysUser().getUserId());
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

    @Override
    public void fileDownload(Long filestorageId, HttpServletResponse response) throws IOException {
        NetworkdiskUserFilestorageVo vo = baseMapper.selectVoById(filestorageId);
        iSysOssService.downloadByUser(vo.getOssId(),response,vo.getOriginalName());
    }

    @Override
    public Boolean removeToRecyclebin(Long[] filestorageIds) {
        SysUser sysUser = iSysUserService.getSysUser();//加载用户信息
        //加载回收站文件容量上限，超出后删除更旧的文件
        int fileSizeMax = 100;
        //根据用户级别获取回收站天数上线
        long fileDayMax = 7;
        //数据写入回收站
        List<NetworkdiskUserFilestorageRecyclebin> recyclebin =
            iNetworkdiskUserFilestorageRecyclebinService.queryByuserId(sysUser.getUserId());
        if ((recyclebin.size() + filestorageIds.length) > fileSizeMax){
            int i = (recyclebin.size() + filestorageIds.length) - fileSizeMax;
            LambdaQueryWrapper<NetworkdiskUserFilestorageRecyclebin> wrapper = new LambdaQueryWrapper<>();
            wrapper.orderByDesc(NetworkdiskUserFilestorageRecyclebin::getCreateTime);
            wrapper.last(" limit " + i);
            for (NetworkdiskUserFilestorageRecyclebin recyclebin1 :recyclebin){
                iNetworkdiskUserFilestorageRecyclebinService.deleteByWrapper(wrapper);
            }
        }
        for (long filestorageId:filestorageIds){
            NetworkdiskUserFilestorageRecyclebinBo bo = BeanUtil.toBean(baseMapper.selectVoById(filestorageId), NetworkdiskUserFilestorageRecyclebinBo.class);
            bo.setFileExpirationDate(fileDayMax);
            iNetworkdiskUserFilestorageRecyclebinService.insertByBo(bo);
        }
        //删除文件列表数据
        return baseMapper.deleteBatchIds(Arrays.asList(filestorageIds)) > 0;
    }
}
