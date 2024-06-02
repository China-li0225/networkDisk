package com.networkDisk.filestorage.task;

import cn.hutool.extra.spring.SpringUtil;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageRecyclebinService;

/**
 * 回收站定时任务
 * ClassName: RecyclebinScheduled <br/>
 * Description: <br/>
 * date: 2024/6/2 17:05<br/>
 *
 * @author lixing<br />
 * @since JDK
 */
public class RecyclebinScheduled {
    /**
     * 定时删除过期数据
     */
    public void DeleteRecyclebinScheduled(){
        INetworkdiskUserFilestorageRecyclebinService iNetworkdiskUserFilestorageRecyclebinService = SpringUtil.getBean(INetworkdiskUserFilestorageRecyclebinService.class);
        //遍历所有超时文件
        iNetworkdiskUserFilestorageRecyclebinService.gettimeoutFile();
    }
}
