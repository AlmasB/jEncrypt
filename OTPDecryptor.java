public class OTPDecryptor extends Translator
{
	/**
	 * Decrypts text with the given key
	 * @param text - text to decrypt
	 * @param key - key to use in decryption
	 * @return decrypted text
	 */
	
	public String process(final String text, final String key)
	{	
		String decrypted = "";
		
		for (int i = 0; i < text.length(); i++)
		{
			decrypted += combine(text.charAt(i), key.charAt(i), '-');
		}
		
		return decrypted;
	}
}
