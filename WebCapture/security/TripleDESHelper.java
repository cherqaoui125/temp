package tp.gwt.comet.shared.security;

import java.security.MessageDigest;
import tp.gwt.comet.shared.exceptions.WebCaptureException;
import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.client.TripleDesCipher;

public class TripleDESHelper 
{
	
	public static String encryptWithTripleDES(String source,String sekretKey) throws WebCaptureException
	{
		
	// Variables
	String encryptedData="";
	TripleDesCipher cipher=null;
	byte[] triple_des_key=null;	
			
		try 
		{
			
			cipher = new TripleDesCipher();
			triple_des_key=generateTripleDESKey(sekretKey);
			cipher.setKey(triple_des_key);
			
			// Encrypts
			encryptedData = cipher.encrypt(source); 
					
		} catch (DataLengthException e1) 
		{
			// Journalisation
			e1.printStackTrace();
			
			// Throw WebCaptureException
			throw new WebCaptureException(e1.getMessage());
			
		} catch (IllegalStateException e2) 
		{
			// Journalisation
			e2.printStackTrace();
			
			// Throw WebCaptureException
			throw new WebCaptureException(e2.getMessage());
			
		} catch (InvalidCipherTextException e3) 
		{
			// Journalisation
			e3.printStackTrace();
			
			// Throw WebCaptureException
			throw new WebCaptureException(e3.getMessage());
			
		}catch(Exception exp4)
		{
						  
			 if (exp4 instanceof WebCaptureException)
			 {
				 
				 throw exp4;
				 				 
			 }
			 else
			 {
				 
				// Journalisation
				exp4.printStackTrace();
				 
				// Throw WebCaptureException
				throw new WebCaptureException(exp4.getMessage());
				 
			 }
				
		}finally
		{
				
			// Release variables	
			cipher=null;
			triple_des_key=null;	
				
		}
		
	// Reply
	return encryptedData;
		
	}
	
	
	public static String decryptWithTripleDES(String encryptedData,String sekretKey) throws WebCaptureException
	{
		
	// Variables
	String decryptedData="";
	TripleDesCipher cipher=null;
	byte[] triple_des_key=null;	
			
		try 
		{
			
			cipher = new TripleDesCipher();
			triple_des_key=generateTripleDESKey(sekretKey);
			cipher.setKey(triple_des_key);
			
			// Decrypt
			decryptedData = cipher.decrypt(encryptedData);
		
		} catch (DataLengthException e1) 
		{
			// Journalisation
			e1.printStackTrace();
			
			// Throw WebCaptureException
			throw new WebCaptureException(e1.getMessage());
			
		} catch (IllegalStateException e2) 
		{
			// Journalisation
			e2.printStackTrace();
			
			// Throw WebCaptureException
			throw new WebCaptureException(e2.getMessage());
			
		} catch (InvalidCipherTextException e3) 
		{
			// Journalisation
			e3.printStackTrace();
			
			// Throw WebCaptureException
			throw new WebCaptureException(e3.getMessage());
			
		}catch(Exception exp4)
		{
						  
			 if (exp4 instanceof WebCaptureException)
			 {
				 
				 throw exp4;
				 				 
			 }
			 else
			 {
				 
				// Journalisation
				exp4.printStackTrace();
				 
				// Throw WebCaptureException
				throw new WebCaptureException(exp4.getMessage());
				 
			 }
				
		}finally
		{
				
			// Release variables	
			cipher=null;
			triple_des_key=null;	
				
		}
		
	// Reply
	return decryptedData;
		
	}
	
	private static byte[] generateTripleDESKey(String keyString) throws WebCaptureException
	{
		
	// Variables
	byte[] keyB=null;
		
	try
	{
		
		// // a Triple DES key is a byte[24] array
		keyB = new byte[24]; 

		for (int i = 0; i < keyString.length() && i < keyB.length; i++) 
		{
			keyB[i] = (byte) keyString.charAt(i);
		}
	   		
	}catch(Exception exp3)
	{
		
		if (exp3 instanceof WebCaptureException)
		 {
			 
			 throw exp3;
			 				 
		 }
		 else
		 {
			 
			// Journalisation
			exp3.printStackTrace();
			 
			// Throw WebCaptureException
			throw new WebCaptureException(exp3.getMessage());
			 
		 }
		
	}finally
	{
		
		// Release variables
		;
		
	}
		
	// Reply
	return keyB;
		
	}
	
	private static String encryptWithMD5 (String plainString) 
	{
		
	// Variables	
	String valueEncrypted="";
	String hex ="";
	byte[] uniqueKey = null;
	byte[] hash = null;
	StringBuffer hashString=null;

	try 
	{
	
	// Formatages
	plainString=plainString.trim();
		
	if (plainString.equals("")==false)
	{
		
	// Conversion de la chaine en entrée dans un tableau byte[]
	uniqueKey = plainString.getBytes();
	
	// on récupère un objet qui permettra de crypter la chaine
	hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
	hashString = new StringBuffer();
	
	for (int i = 0; i < hash.length; ++i) 
	{
	
	hex = Integer.toHexString(hash[i]);
	
	if (hex.length() == 1) 
	{
		hashString.append("0");
		hashString.append(hex.charAt(hex.length() - 1));
	}
	else 
	{
		
		hashString.append(hex.substring(hex.length() - 2));
		
	}
	
	}
	
	valueEncrypted=hashString.toString();
	
	} // Mot de passe en entrée non NULL
	
	}catch (Exception e) 
	{
		
		// RAZ
		valueEncrypted="";
		
		// Journalisation
		//logger.severe("web.capture.client.util.WebCaptureHelper.encryptPassword()...");
		//logger.severe(e.getMessage());
		//logger.severe(web.capture.client.util.WebCaptureHelper.formatStackTrace2String(e));
		
	}finally
	{
		
		uniqueKey = null;
		hash = null;
		hashString=null;
		
	}

	return valueEncrypted;
			
	}
	
	
	

}
