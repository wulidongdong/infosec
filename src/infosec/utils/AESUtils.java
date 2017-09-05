/**

 * <����>

 * @author <����>

 * @date 2014-10-8 ����3:24:14

 * DESUtils

 */

package infosec.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

import sun.misc.BASE64Encoder;

public class AESUtils {

	private static Key key;

	private static String KEY_STR = "myKeyInfosec";// ��Կ

	private static String CHARSETNAME = "UTF-8";// ����

	private static String ALGORITHM = "AES";// ��������
	
    /** 
     * ���� 
     *  
     * @param content ��Ҫ���ܵ����� 
     * @return 
     */  
    public static String encrypt(String content) {  
    	BASE64Encoder base64encoder = new BASE64Encoder();
            try {             
                    KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);  
                    kgen.init(128, new SecureRandom(KEY_STR.getBytes()));  
                    SecretKey secretKey = kgen.generateKey();  
                    byte[] enCodeFormat = secretKey.getEncoded();  
                    SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);  
                    Cipher cipher = Cipher.getInstance(ALGORITHM);// ����������  
                    byte[] byteContent = content.getBytes(CHARSETNAME);  
                    cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��  
                    byte[] result = cipher.doFinal(byteContent);  
                    return base64encoder.encode(result); // ����  
            } catch (NoSuchAlgorithmException e) {  
                    e.printStackTrace();  
            } catch (NoSuchPaddingException e) {  
                    e.printStackTrace();  
            } catch (InvalidKeyException e) {  
                    e.printStackTrace();  
            } catch (UnsupportedEncodingException e) {  
                    e.printStackTrace();  
            } catch (IllegalBlockSizeException e) {  
                    e.printStackTrace();  
            } catch (BadPaddingException e) {  
                    e.printStackTrace();  
            }  
            return null;  
    }  

 
 

    /**���� 
     * @param content  ���������� 
     * @return 
     */  
    public static String decrypt(String str) { 
    		BASE64Decoder base64decoder = new BASE64Decoder();
            try {  
            		 byte[] content = base64decoder.decodeBuffer(str);
                     KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);  
                     kgen.init(128, new SecureRandom(KEY_STR.getBytes()));  
                     SecretKey secretKey = kgen.generateKey();  
                     byte[] enCodeFormat = secretKey.getEncoded();  
                     SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);              
                     Cipher cipher = Cipher.getInstance(ALGORITHM);// ����������  
                    cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��  
                    byte[] result = cipher.doFinal(content);  
                    return new String(result); // ����  
            } catch (NoSuchAlgorithmException e) {  
                    e.printStackTrace();  
            } catch (NoSuchPaddingException e) {  
                    e.printStackTrace();  
            } catch (InvalidKeyException e) {  
                    e.printStackTrace();  
            } catch (IllegalBlockSizeException e) {  
                    e.printStackTrace();  
            } catch (BadPaddingException e) {  
                    e.printStackTrace();  
            } catch (IOException e) {
					e.printStackTrace();
			}  
            return null;  
    }

public static void main(String[] args) {

    String content = "infosec";  
    //����  
    System.out.println("����ǰ��" + content);  
    String encryptResult = encrypt(content);
    System.out.println("���ܺ�" + encryptResult);  
    
    //����  
    String decryptResult = decrypt(encryptResult);  
    System.out.println("���ܺ�" + decryptResult);  
}

}