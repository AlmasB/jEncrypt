public class OTPEncryptor extends Translator 
{
	private String key = "";
	
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
	
	public String getKey()
	{
		return key;
	}
}
