package cn.anicert.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;


public class AESUtils {
    private static final byte[] key = UUID.randomUUID().toString().substring(0, 16).getBytes();


    // 加密:
    public static byte[] encrypt(byte[] input) {
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


    public static byte[] decrypt(byte[] input) {
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
