package com.mm.security.rsa;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author: Shmily
 * @date: 2018/1/18 18:18
 **/
public class JdkRSA {

    private RSAPublicKey rsaPublicKey = null;
    private RSAPrivateKey rsaPrivateKey = null;

    //初始化密钥
    public JdkRSA() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("PublicKey:"+ Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));
            System.out.println("PrivateKey:"+Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 签名
     *
     * @param param
     * @return
     */
    public byte[] sign(byte[] param) {
        byte[] result = null;
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(param);
            result = signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 验证签名
     *
     * @param param
     * @param signResult
     * @return
     */
    public boolean verify(byte[] param, byte[] signResult) {
        boolean result = false;
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(param);
            result = signature.verify(signResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
