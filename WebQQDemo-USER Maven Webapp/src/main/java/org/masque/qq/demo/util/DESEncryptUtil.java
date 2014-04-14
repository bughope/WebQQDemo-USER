package org.masque.qq.demo.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * DES加密、解密工具类
 * 
 * @author 
 *
 */
public class DESEncryptUtil {
	//加密解密的密钥，用了这个密钥加密就必须用这个密钥解密，也可以自己定义密钥
	public static final String DEF_DES = "131264542203141592653589";
	/**
	 * 加密
	 * 
	 * @param myKey
	 * @param input
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String enc(String myKey, String input) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(myKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		byte data[] = input.getBytes();
		byte[] encryptedData = cipher.doFinal(data);
		return HEXUtil.byte2hex(encryptedData);
	}
	/**
	 * 解密
	 * 
	 * @param myKey
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String dec(String myKey, String input) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(myKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		byte decryptedData[] = cipher.doFinal(HEXUtil.hex2byte(input));
		return new String(decryptedData);
	}

	public static void main(String[] args) throws Exception {
	    //str为需要加密的内容，enc是对内容进行加密，dec是对内容进行解密
		String str = "123456";
		String outString = enc(DEF_DES, str);
		System.out.println(outString);
		System.out.println(dec(DEF_DES, "9A62FE5229E79936CE10DF59D845AAC0"));
	}
}