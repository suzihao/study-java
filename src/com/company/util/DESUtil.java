package com.company.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @Author: suhe
 * @Date: 2026/5/23 11:29
 */
public class DESUtil {

    private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";
    private static final String ALGORITHM = "DES";
    private static final String PREFIX = "NA";
    private static final int DES_LENGTH = 8;

    private DESUtil() {
    }

    public static String decryptHex(String encryptedHex, String clientId) {
        String secret = buildSecret(clientId);
        return decryptHex(encryptedHex, secret, secret);
    }

    public static String decryptHex(String encryptedHex, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec secretKeySpec = new SecretKeySpec(toDesBytes(key), ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(toDesBytes(iv));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decrypted = cipher.doFinal(hexToBytes(encryptedHex));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception exception) {
            throw new IllegalArgumentException("DES解密失败，请检查Hex串、clientId或密钥/IV是否正确", exception);
        }
    }

    public static boolean canDecrypt(String encryptedHex, String clientId) {
        try {
            decryptHex(encryptedHex, clientId);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static String buildSecret(String clientId) {
        if (clientId == null || clientId.length() < 6) {
            throw new IllegalArgumentException("clientId长度不能少于6位");
        }
        return PREFIX + clientId.substring(0, 6);
    }

    public static void main(String[] args) {
//        if (args.length < 2) {
//            throw new IllegalArgumentException("用法: DESUtil <encryptedHex> <clientId>");
//        }
        String encryptedHex = "63d8e7ce6f100fc7e058f949deb0cf40";
        String clientId = "RC0qBERs5jeGtwxKwGdO";
        String secret = buildSecret(clientId);
        String decrypted = decryptHex(encryptedHex, secret, secret);
        System.out.println("secret=" + secret);
        System.out.println("result=" + decrypted);
    }

    private static byte[] toDesBytes(String value) {
        if (value == null || value.length() != DES_LENGTH) {
            throw new IllegalArgumentException("DES密钥和IV长度必须为8位字符");
        }
        return value.getBytes(StandardCharsets.UTF_8);
    }

    private static byte[] hexToBytes(String hex) {
        if (hex == null || hex.isBlank()) {
            throw new IllegalArgumentException("Hex密文不能为空");
        }
        String normalized = hex.trim();
        if ((normalized.length() & 1) != 0) {
            throw new IllegalArgumentException("Hex密文长度必须为偶数");
        }
        byte[] result = new byte[normalized.length() / 2];
        for (int i = 0; i < normalized.length(); i += 2) {
            int high = Character.digit(normalized.charAt(i), 16);
            int low = Character.digit(normalized.charAt(i + 1), 16);
            if (high < 0 || low < 0) {
                throw new IllegalArgumentException("Hex密文包含非法字符");
            }
            result[i / 2] = (byte) ((high << 4) + low);
        }
        return result;
    }
}
