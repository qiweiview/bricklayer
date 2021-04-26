package com.anicert.utils;

import com.management.utils.MessageRuntimeException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AESUtils {
    private  static final  byte[] key = "`17654@435!90831".getBytes();


    // 加密:
    public static byte[] encrypt( byte[] input)  {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey keySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return cipher.doFinal(input);
        } catch (Exception e) {
           throw new MessageRuntimeException("encrypt fail");
        }

    }


    public static byte[] decrypt( byte[] input) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey keySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return cipher.doFinal(input);
        } catch (Exception e) {
            throw new MessageRuntimeException("decrypt fail");
        }

    }
}
