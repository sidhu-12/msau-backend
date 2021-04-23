package com.accolite.msaumanagement.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;



//Utility used for SecurePasswordGeneration
@Component
public class SecurePasswordGeneration{
	public String get_SHA_512_SecurePassword(String passwordToHash, String salt,String hashAlgorithm) throws NoSuchAlgorithmException{
		final Logger logger = LogManager.getLogger(SecurePasswordGeneration.class);
    String generatedPassword = null;
    try {
        MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
        logger.error("NO SUCH ALGORITHM EXCEPTION ");
        throw new NoSuchAlgorithmException("Give Appropriate Algorithm");
    }
    return generatedPassword;
	}
}