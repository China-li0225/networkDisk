package com.networkDisk.common.encrypt.encryptor;

import com.networkDisk.common.encrypt.EncryptContext;
import com.networkDisk.common.enums.AlgorithmType;
import com.networkDisk.common.enums.EncodeType;
import com.networkDisk.common.utils.EncryptUtils;

/**
 * Base64算法实现
 *
 * @author 老马
 * @version 4.6.0
 */
public class Base64Encryptor extends AbstractEncryptor {

    public Base64Encryptor(EncryptContext context) {
        super(context);
    }

    /**
     * 获得当前算法
     */
    @Override
    public AlgorithmType algorithm() {
        return AlgorithmType.BASE64;
    }

    /**
     * 加密
     *
     * @param value      待加密字符串
     * @param encodeType 加密后的编码格式
     */
    @Override
    public String encrypt(String value, EncodeType encodeType) {
        return EncryptUtils.encryptByBase64(value);
    }

    /**
     * 解密
     *
     * @param value      待加密字符串
     */
    @Override
    public String decrypt(String value) {
        return EncryptUtils.decryptByBase64(value);
    }
}
