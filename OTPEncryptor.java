public class OTPEncryptor extends Translator 
{
	private String key = "";
	
	/**
	 * Encrypts any text passed to it
	 * @param text - text to encrypt
	 * @return - encrypted text
	 */
	
	public String process(final String text)
	{
		key = generateKey(text.length());
		
		String encrypted = "";
		
		for (int i = 0; i < text.length(); i++)
		{
			encrypted += combine(text.charAt(i), key.charAt(i), '+');
		}
		
		return encrypted;
	}
	
	/**
	 * Gets the generated key
	 * @return - key
	 */
	
	public String getKey()
	{
		return key;
	}
}
