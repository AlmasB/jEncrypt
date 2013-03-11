public class OTPDecryptor extends Translator
{
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
