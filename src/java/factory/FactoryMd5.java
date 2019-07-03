/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @Referencia do codigo http://www.devmedia.com.br/criptografia-md5/2944
 */
public class FactoryMd5 {
 
    private static MessageDigest md5 = null;
 
    
    //Declaração  de metodo estatico!!!!!!
    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
 
  private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;
 
        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 2,hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
    }
 
public static String criptografar(String pwd) {
        if (md5 != null) {
            return new String(hexCodes(md5.digest(pwd.getBytes())));
        }
        return null;
    }
}
