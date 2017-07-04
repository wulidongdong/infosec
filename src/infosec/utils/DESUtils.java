/**

 * <√Ë ˆ>

 * @author <–’√˚>

 * @date 2014-10-8 œ¬ŒÁ3:24:14

 * DESUtils

 */

package infosec.utils;

import java.security.Key;

import java.security.SecureRandom;

import javax.crypto.Cipher;

import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;

import sun.misc.BASE64Encoder;

public class DESUtils {

	private static Key key;

	private static String KEY_STR = "myKeyInfosec";// √‹‘ø

	private static String CHARSETNAME = "UTF-8";// ±‡¬Î

	private static String ALGORITHM = "DES";// º”√‹¿‡–Õ

	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



public static String getEncryptString(String str) {


	BASE64Encoder base64encoder = new BASE64Encoder();
	try {
		byte[] bytes = str.getBytes(CHARSETNAME);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] doFinal = cipher.doFinal(bytes);
		return base64encoder.encode(doFinal);
		
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}


public static String getDecryptString(String str) {

	BASE64Decoder base64decoder = new BASE64Decoder();

	try {
		byte[] bytes = base64decoder.decodeBuffer(str);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] doFinal = cipher.doFinal(bytes);
		return new String(doFinal, CHARSETNAME);
	} catch (Exception e) {
		throw new RuntimeException(e);
	}

}

public static void main(String[] args) {

	String cod = getEncryptString("infosec");
	System.out.println(cod);
	String dec = getDecryptString(cod);
	System.out.println(dec);
}

}