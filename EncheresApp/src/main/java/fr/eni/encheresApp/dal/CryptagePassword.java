package fr.eni.encheresApp.dal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptagePassword {

	public static String crypteString(String mdp) {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(mdp.getBytes());

		byte byteData[] = md.digest();

		StringBuffer buffer = new StringBuffer();
		for (byte b : byteData) {
			buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

		}
		return buffer.toString();
	}
}
