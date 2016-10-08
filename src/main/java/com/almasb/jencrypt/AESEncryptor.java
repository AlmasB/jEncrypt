package com.almasb.jencrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Uses Advanced Encryption Standard.
 *
 * @author Almas Baimagambetov
 */
public final class AESEncryptor {

    private SecretKeyFactory factory = null;
    private SecureRandom random = new SecureRandom();

    /**
     * Encrypts given bytes with AES algorithm and protects
     * it with given password
     *
     * @param data
     * @param password
     * @return encrypted data
     */
    public byte[] encrypt(byte[] data, char[] password) {
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        try {
            if (factory == null) factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey tmp = factory.generateSecret(new PBEKeySpec(password, salt, 65536, 128));
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);

            byte[] iv = cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
            byte[] ciphertext = cipher.doFinal(data);
            byte[] encryptedData = new byte[8 + 16 + ciphertext.length];

            int index = 0;

            for (int i = 0; i < salt.length; i++)
                encryptedData[index++] = salt[i];

            for (int i = 0; i < iv.length; i++)
                encryptedData[index++] = iv[i];

            for (int i = 0; i < ciphertext.length; i++)
                encryptedData[index++] = ciphertext[i];

            return encryptedData;
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt: " + e);
        }
    }

    /**
     * Decrypts data which has been previously encrypted
     *
     * @param encryptedData
     * @param password
     * @return decrypted data
     */
    public byte[] decrypt(byte[] encryptedData, char[] password) {
        byte[] salt = new byte[8];
        byte[] iv = new byte[16];
        byte[] data = new byte[encryptedData.length - 16 - 8];

        try {
            int index = 0;
            for (int i = 0; i < salt.length; i++)
                salt[i] = encryptedData[index++];

            for (int i = 0; i < iv.length; i++)
                iv[i] = encryptedData[index++];

            for (int i = 0; i < data.length; i++)
                data[i] = encryptedData[index++];

            if (factory == null) factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            SecretKey tmp = factory.generateSecret(new PBEKeySpec(password, salt, 65536, 128));
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));

            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt: " + e);
        }
    }
}
