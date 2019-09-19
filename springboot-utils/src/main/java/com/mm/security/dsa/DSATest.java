package com.mm.security.dsa;

import org.apache.commons.codec.binary.Hex;

/**
 * @author: shmily
 * @date: 2018/1/18 18:18
 **/
public class DSATest {
    private static String str = "shmily";
    public static void main(String[] args) {
        System.out.println("======================= JdkDSA数字签名 ========================");
        long date = System.currentTimeMillis();
        JdkDSA jdkDsa = new JdkDSA();
        byte[] sign = jdkDsa.sign(str.getBytes());
        System.out.println(new String(Hex.encodeHexString(sign)));
        boolean verify = jdkDsa.verify(str.getBytes(),sign);
        System.out.println(verify);
        long between = System.currentTimeMillis() - date;
        System.out.println("耗时："+ between);
    }
}
