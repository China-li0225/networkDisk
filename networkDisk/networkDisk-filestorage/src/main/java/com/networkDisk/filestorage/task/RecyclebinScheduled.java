package com.networkDisk.filestorage.task;

import cn.hutool.extra.spring.SpringUtil;
import com.networkDisk.filestorage.service.INetworkdiskUserFilestorageRecyclebinService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 回收站定时任务
 * ClassName: RecyclebinScheduled <br/>
 * Description: <br/>
 * date: 2024/6/2 17:05<br/>
 *
 * @author lixing<br />
 * @since JDK
 */
@Log4j2
@Component
public class RecyclebinScheduled {
    /**
     * 定时删除用户回收站过期文件
     */
    @Scheduled(cron = "0 0 1 * * ?")//每天凌晨一点执行
    @PostConstruct//程序启动立即执行
    @Async("threadPoolTaskExecutor")
    @Transactional(rollbackFor = Exception.class)
    public void DeleteRecyclebinScheduled(){
        log.info("开始执行定时任务：清理回收站过期文件");
        INetworkdiskUserFilestorageRecyclebinService iNetworkdiskUserFilestorageRecyclebinService = SpringUtil.getBean(INetworkdiskUserFilestorageRecyclebinService.class);
        //遍历所有超时文件
        List<Long> expirationIds = iNetworkdiskUserFilestorageRecyclebinService.gettimeoutFile();
        //删除文件
        if (expirationIds.size() > 0) {
            iNetworkdiskUserFilestorageRecyclebinService.deleteWithValidByIds(expirationIds,true);
        }else {
            log.info("不存在需要删除的文件");
        }
        log.info("结束执行定时任务：清理回收站过期文件");
    }
}
