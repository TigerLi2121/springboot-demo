package com.mm.security.ecdsa;

import org.apache.commons.codec.binary.Hex;

/**
 * @author: Shmily
 * @date: 2018/1/18 18:18
 **/
public class ECDSATest {
    private static String str = "shmily";
    public static void main(String[] args) {
        System.out.println("======================= JdkECDSA数字签名 ========================");
        long date = System.currentTimeMillis();
        JdkECDSA jdkECDSA = new JdkECDSA();
        byte[] sign = jdkECDSA.sign(str.getBytes());
        System.out.println(new String(Hex.encodeHexString(sign)));
        boolean verify = jdkECDSA.verify(str.getBytes(),sign);
        System.out.println(verify);
        long between = System.currentTimeMillis() - date;
        System.out.println("耗时："+ between);
    }
}
