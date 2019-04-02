package com.mm.security.dsa;

import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author: Shmily
 * @date: 2018/1/18 18:18
 **/
public class JdkDSA {
    private DSAPublicKey dsaPublicKey = null;
    private DSAPrivateKey dsaPrivateKey = null;

    //初始化密钥
    public JdkDSA() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
            dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();
            System.out.println("PublicKey:"+ Base64.getEncoder().encodeToString(dsaPublicKey.getEncoded()));
            System.out.println("PrivateKey:"+Base64.getEncoder().encodeToString(dsaPrivateKey.getEncoded()));
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
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withDSA");
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
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withDSA");
            signature.initVerify(publicKey);
            signature.update(param);
            result = signature.verify(signResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
