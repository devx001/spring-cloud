package com.devx.gateway.utils;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtils {


  public static PublicKey getPublicKey(String base64PublicKey) {
    PublicKey publicKey = null;
    try {
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
          Base64.getDecoder().decode(base64PublicKey.getBytes()));
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      publicKey = keyFactory.generatePublic(keySpec);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return publicKey;
  }

  public static PrivateKey getPrivateKey(String base64PrivateKey) {
    PrivateKey privateKey = null;
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
        Base64.getDecoder().decode(base64PrivateKey.getBytes()));
    KeyFactory keyFactory = null;
    try {
      keyFactory = KeyFactory.getInstance("RSA");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    try {
      privateKey = keyFactory.generatePrivate(keySpec);
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return privateKey;
  }

  public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
    return cipher.doFinal(data.getBytes());
  }

  public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return new String(cipher.doFinal(data));
  }


  public static void main(String[] args) throws Exception {
    PublicKey publicKey = RSAUtils.getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtXMsqdRZmJtARq0nEHSL9bET1DAZaYvqehzKCycuvpt6LFBlJkRXlKAQSqa9lkHHEAOvxzysFhKSQ18+EeL78x00t1wL1f/lmuTV73VnR97rAOOBqg06mMlg0oule+X+pAjzd7fr2CtEoiOpPU5Z0NNX9TQM1O7+Ad2hBpvXADdRa1DHjcZQuRhuE5vMQKLQrfGuPEvUTn1Vz7A16MVdRtKs2e9KpxN1pNgFNxt+/gJcBceWtj88iDc9Lyg1xwd5+U5XC6RH7jYsI34W/rRKXPvGwLBf+VMykbXg7BcifoiyVD7JMCV042eB+1Tf1tDufM4+bNuC8XsKW7rD5NjdtQIDAQAB");
    System.out.println(RSAEncryptionJava.encryptFront("{\n"
        + "\t\"name\": \"Juan\",\n"
        + "\t\"lastName\": \"Pedraza\",\n"
        + "\t\"phoneNumber\": \"3105762222\"\n"
        + "}", publicKey));
  }

}
