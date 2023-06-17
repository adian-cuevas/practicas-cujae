/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Alfonso
 */
public class Crypto {
    /***
	 * Este método encripta un mensaje de texto mediante el algoritmo MD5.
	 * 
	 * @param text
	 *            Texto a encriptar
	 * @return Texto encriptado
	 */
	public static String getEncryptedSHAString(String text) {

		byte[] digest = null;
		byte[] buffer = text.getBytes();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.reset();
			messageDigest.update(buffer);
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println("Error creando Digest");
		}

		return toHexadecimal(digest);
	}
        
        /**
	 * Este método convierte un arreglo de bytes a String usando valores hexadecimales
	 * 
	 * @param digest
	 *            Arreglo de bytes a convertir
	 * @return Cadena de texto creado a partir de <code>digest</code>
	 */
	private static String toHexadecimal(byte[] digest) {
		String hash = "";
		for (byte aux : digest) {
			int b = aux & 0xff;
			if (Integer.toHexString(b).length() == 1)
				hash += "0";
			hash += Integer.toHexString(b);
		}
		return hash;
	}
}
