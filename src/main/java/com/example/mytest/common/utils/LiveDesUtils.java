package com.example.mytest.common.utils;

import cn.hutool.core.codec.Base62;
import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * wm签名工具类
 */
public class LiveDesUtils {

    private static final Logger LOG = LoggerFactory.getLogger(LiveDesUtils.class);

    // 向量
    private final static String iv = "CqMfe4eo";

    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText, String secretKey) throws Exception {
        Cipher cipher = getCipher5Key(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptData = new byte[0];
        try {
            encryptData = cipher.doFinal(plainText.getBytes(Charsets.UTF_8.name()));
            return Base62.encode(encryptData);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText, String key) {
        Cipher cipher = getCipher5Key(Cipher.DECRYPT_MODE, key);
        byte[] decryptData = new byte[0];
        try {
            decryptData = cipher.doFinal(Base62.decode(encryptText));
            return new String(decryptData, Charsets.UTF_8.name());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return StringUtils.EMPTY;
    }

    private static Cipher getCipher5Key(int decryptMode, String key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DESede");
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(decryptMode, secretKey, ips);
            return cipher;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String... args) throws Exception {
//        String accessKeySecret = "1IvFcjTxtXyD8Ki1nPbm1Ipf";        //107
        String accessKeySecret = "1uAUm4qPv1sLyHyophU5jR1k";        //144

//        String encryStr = "3rIHW6hehdWvO9ejHJztM70ixbhHb6hKzSNcG5naSWf6OkCP7p5u5TSayJ205zHla";
//        String decode = decode(encryStr, accessKeySecret);
//        System.out.println(decode);
//        //encode
        String code = GroupCodeUtils.genCode(100980, GroupCodeUtils.CodeType.ADMIN);
        System.out.println("code: " + code);
        StringBuilder sb = new StringBuilder();
        sb.append("107").append(",")//appId
                .append(code).append(",")//码
                .append("1609732949278201").append(",")//userid
                .append("" + System.currentTimeMillis() / 1000);//时间戳
        String encode = encode(sb.toString(), accessKeySecret);
        System.out.println(encode);

        GroupCodeUtils.ParseCodeResult parseCodeResult = GroupCodeUtils.parseCode("JNUEKDX8");
        System.out.println("roomId:  " + parseCodeResult.toString());

    }

}
