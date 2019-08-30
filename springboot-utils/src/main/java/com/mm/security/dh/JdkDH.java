package com.mm.security.dh;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author: shmily
 * @date: 2018/1/18 18:18
 **/
public class JdkDH {
    //发送方
    private PublicKey senderPublicKey = null;
    private PrivateKey senderPrivateKey = null;

    //生成发送公钥、私钥
    public JdkDH() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            senderPublicKey = keyPair.getPublic();
            senderPrivateKey = keyPair.getPrivate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取发送方公钥
     *
     * @return
     */
    public byte[] getSenderPublicKeyEnc() {
        return senderPublicKey.getEncoded();
    }

    /**
     * 发送方加密
     *
     * @param param
     * @param receiverPublicKeyEnc
     * @return
     */
    public byte[] senderEncrypt(byte[] param, byte[] receiverPublicKeyEnc) {
        System.out.println("发送方私钥：" + Base64.getEncoder().encodeToString(senderPrivateKey.getEncoded()));
        byte[] result = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(senderPrivateKey);
            keyAgreement.doPhase(publicKey, true);
            SecretKey secretKey = keyAgreement.generateSecret("DES");
            System.out.println("发送方 secretKey：" + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            result = cipher.doFinal(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //接收方
    private PublicKey receiverPublicKey = null;
    private PrivateKey receiverPrivateKey = null;

    /**
     * 获取接收方公钥
     *
     * @param senderPublicKeyEnd
     * @return
     */
    public byte[] getReceiverPublicKeyEnc(byte[] senderPublicKeyEnd) {
        byte[] result = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnd);
            receiverPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
            keyPairGenerator.initialize(dhParameterSpec);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            receiverPrivateKey = keyPair.getPrivate();
            result = keyPair.getPublic().getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 接收方解密
     *
     * @param param
     * @return
     */
    public byte[] receiverDecrypt(byte[] param) {
        System.out.println("接收方私钥：" + Base64.getEncoder().encodeToString(receiverPrivateKey.getEncoded()));
        byte[] result = null;
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(receiverPrivateKey);
            keyAgreement.doPhase(receiverPublicKey,true);
            SecretKey secretKey = keyAgreement.generateSecret("DES");
            System.out.println("接收方 secretKey：" + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            result = cipher.doFinal(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
