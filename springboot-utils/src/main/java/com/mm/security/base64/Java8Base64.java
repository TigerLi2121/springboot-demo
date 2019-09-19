package com.mm.security.base64;

import java.util.Base64;

/**
 * @author: shmily
 * @date: 2018/1/18 18:18
 **/
public class Java8Base64 {

    /**
     * 编码
     * @param param
     * @return
     */
    public static byte[] encoder(byte[] param) {
        return Base64.getEncoder().encode(param);
    }

    /**
     * 解码
     * @param param
     * @return
     */
    public static byte[] decoder(byte[] param) {
        return Base64.getDecoder().decode(param);
    }
}
