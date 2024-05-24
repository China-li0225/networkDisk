package com.networkDisk.common.translation.impl;

import com.networkDisk.common.annotation.TranslationType;
import com.networkDisk.common.constant.TransConstant;
import com.networkDisk.common.core.service.OssService;
import com.networkDisk.common.translation.TranslationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * OSS翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.OSS_ID_TO_URL)
public class OssUrlTranslationImpl implements TranslationInterface<String> {

    private final OssService ossService;

    @Override
    public String translation(Object key, String other) {
        return ossService.selectUrlByIds(key.toString());
    }
}
