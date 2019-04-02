package com.mm.security.base64;

/**
 * @author: Shmily
 * @date: 2018/1/18 18:18
 **/
public class Base64Test {
    private static String str = "shmily";

    public static void main(String[] args) {
        //Java8Base64
        System.out.println("========================= Java8Base64 =========================");
        long dateJava8 = System.currentTimeMillis();
        String java8Encoder = new String(Java8Base64.encoder(str.getBytes()));
        System.out.println(str + " Java8Base64.encoder:" + java8Encoder);
        String java8Decoder = new String(Java8Base64.decoder(java8Encoder.getBytes()));
        System.out.println(java8Encoder + " Java8Base64.decoder:" + java8Decoder);
        long betweenJava8 = System.currentTimeMillis() - dateJava8;
        System.out.println("耗时：" + betweenJava8);
    }
}
