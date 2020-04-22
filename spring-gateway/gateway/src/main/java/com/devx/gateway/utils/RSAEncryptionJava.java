package com.devx.gateway.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

// Java example for RSA encryption/decryption.
// Uses Apache commons codec library
// Uses strong encryption with 2048 key size.
public class RSAEncryptionJava {

  public static void writeToFile(String path, byte[] key) throws IOException {
    File f = new File(path);
    f.getParentFile().mkdirs();

    FileOutputStream fos = new FileOutputStream(f);
    fos.write(key);
    fos.flush();
    fos.close();
  }

  public static void main(String[] args) throws Exception {
    String plainText = "Hello World!";

    // Generate public and private keys using RSA
    Map<String, Object> keys = getRSAKeys();

    PrivateKey privateKey = (PrivateKey) keys.get("private");
    PublicKey publicKey = (PublicKey) keys.get("public");

    String encryptedText = encryptMessage(plainText, privateKey);
    String descryptedText = decryptMessage(encryptedText, publicKey);

    System.out.println("input:" + plainText);
    System.out.println("encrypted:" + encryptedText);
    System.out.println("decrypted:" + descryptedText);

    writeToFile("RSA/publicKey", publicKey.getEncoded());
    writeToFile("RSA/privateKey", privateKey.getEncoded());

    System.out.println(java.util.Base64.getEncoder().encodeToString(publicKey.getEncoded()));
    System.out.println(
        java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded()));

  }

  // Get RSA keys. Uses key size of 2048.
  private static Map<String, Object> getRSAKeys() throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    PrivateKey privateKey = keyPair.getPrivate();
    PublicKey publicKey = keyPair.getPublic();

    Map<String, Object> keys = new HashMap<String, Object>();
    keys.put("private", privateKey);
    keys.put("public", publicKey);
    return keys;
  }

  // Decrypt using RSA public key
  public static String decryptMessage(String encryptedText, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    return new String(cipher.doFinal(Base64.decodeBase64(encryptedText)));
  }

  // Encrypt using RSA private key
  public static String encryptMessage(String plainText, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    return Base64.encodeBase64String(cipher.doFinal(plainText.getBytes()));
  }

  // Decrypt using RSA public key
  public static String decryptBack(String encryptedText, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return new String(cipher.doFinal(Base64.decodeBase64(encryptedText)));
  }

  // Encrypt using RSA private key
  public static String encryptFront(String plainText, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return Base64.encodeBase64String(cipher.doFinal(plainText.getBytes()));
  }

}