/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 *
 * @author vitom
 */
public class EncryptionUtils {
    private EncryptionUtils(){}
    
    public static String encryptSHA256(char[] password){
    try {
            //retrieve instance of the encryptor of SHA-256
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
//retrieve bytes to encrypt
            
           // byte[] encodedhash = digestor.digest( password.getBytes(StandardCharsets.UTF_8));
           byte[] encodedhash = digestor.digest(toBytes(password));

            StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);
//perform encryption
            for (int i = 0; i < encodedhash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodedhash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
//return encrypted value
            return encryptionValue.toString();
} catch (Exception ex) {
            return ex.getMessage();
        }
    }
    private static byte[] toBytes(char[] chars){
        CharBuffer charBuffer=CharBuffer.wrap(chars);
        ByteBuffer byteBuffer=Charset.forName(StandardCharsets.UTF_8.toString()).encode(charBuffer);
        byte[] bytes=Arrays.copyOfRange(byteBuffer.array(),byteBuffer.position(),byteBuffer.limit());
        Arrays.fill(byteBuffer.array(), (byte)0); //clear sensitive info
        return bytes;
    }
    
    
    
     public static String encryptSHA256(String password) {
        try {
            //retrieve instance of the encryptor of SHA-256
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
//retrieve bytes to encrypt
            byte[] encodedhash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);
//perform encryption
            for (int i = 0; i < encodedhash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodedhash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
//return encrypted value
            return encryptionValue.toString();
} catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
