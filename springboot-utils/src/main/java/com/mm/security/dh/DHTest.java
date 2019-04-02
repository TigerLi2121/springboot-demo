package com.mm.security.dh;

import java.util.Base64;

/**
 * @author: Shmily
 *
 * 配置JVM的系统变量：-Djdk.crypto.KeyAgreement.legacyKDF=true
 * @date: 2018/1/18 18:18
 **/
public class DHTest {
    private static String str = "shmily";

    public static void main(String[] args) {
        System.out.println("======================= JdkDH加密解密 ========================");
        long date = System.currentTimeMillis();
        JdkDH jdkDH = new JdkDH();
        byte[] senderPublicKey = jdkDH.getSenderPublicKeyEnc();
        System.out.println("发送方公钥：" + Base64.getEncoder().encodeToString(senderPublicKey));
        byte[] receiverPublicKey = jdkDH.getReceiverPublicKeyEnc(senderPublicKey);
        System.out.println("接收方公钥：" + Base64.getEncoder().encodeToString(receiverPublicKey));
        byte[] encrypt = jdkDH.senderEncrypt(str.getBytes(), receiverPublicKey);
        System.out.println("加密：" + Base64.getEncoder().encodeToString(encrypt));
        byte[] decrypt = jdkDH.receiverDecrypt(encrypt);
        System.out.println("解密：" + new String(decrypt));
        long between = System.currentTimeMillis() - date;
        System.out.println("耗时："+between);
    }
}
