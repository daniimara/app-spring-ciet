package br.com.treinamento;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

	public MessageDigest messageDig;
	
	public String MD5(String chave) {
		
		try {
			messageDig = MessageDigest.getInstance("MD5");
			messageDig.update(chave.getBytes(),0,chave.length());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		return new BigInteger(1,messageDig.digest()).toString(16);
	}
}
