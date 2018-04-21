package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**
	 * ��Դ�ַ���ͨ��MD5���м���Ϊ�ֽ�����
	 * 
	 * @param source
	 * @return
	 */
	public static byte[] encodeToBytes(String source) {
		byte[] result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();// ����
			md.update(source.getBytes("UTF-8"));// �����Ҫ���ܵ�Դ
			result = md.digest();// ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ��Դ�ַ���ͨ��MD5���ܳ�32λ16������
	 * 
	 * @param source
	 * @return
	 */
	public static String encodeToHex(String source) {
		byte[] data = encodeToBytes(source);// �ȼ���Ϊ�ֽ�����
		StringBuffer hexSb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() == 1) {
				hexSb.append("0");
			}
			hexSb.append(hex);
		}
		return hexSb.toString();
	}

	/**
	 * ��֤�ַ����Ƿ�ƥ��
	 * 
	 * @param unknown
	 *            ����֤���ַ���
	 * @param okHex
	 *            ʹ��MD5���ܺ��16�����ַ���
	 * @return
	 */
	public static boolean validate(String unknown, String okHex) {
		return okHex.equals(encodeToHex(unknown));
	}
}
