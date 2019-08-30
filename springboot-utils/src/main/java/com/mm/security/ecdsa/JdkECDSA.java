package com.mm.security.ecdsa;

import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author: shmily
 * @date: 2018/1/18 18:18
 **/
public class JdkECDSA {
    private ECPublicKey ecPublicKey = null;
    private ECPrivateKey ecPrivateKey = null;

    //初始化密钥
    public JdkECDSA() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            ecPublicKey = (ECPublicKey) keyPair.getPublic();
            ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();
            System.out.println("PublicKey:"+ Base64.getEncoder().encodeToString(ecPublicKey.getEncoded()));
            System.out.println("PrivateKey:"+Base64.getEncoder().encodeToString(ecPrivateKey.getEncoded()));
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
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withECDSA");
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
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withECDSA");
            signature.initVerify(publicKey);
            signature.update(param);
            result = signature.verify(signResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
