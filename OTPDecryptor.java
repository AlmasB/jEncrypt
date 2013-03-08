public class OTPDecryptor extends Translator
{
	public String process(String text, String key)
	{
		String innerKey = getInnerKey(text.length(), key);
		
		String decrypted = "";
		
		for (int i = 0; i < text.length(); i++)
		{
			decrypted += combine(text.charAt(i), innerKey.charAt(i), '-');
		}
		
		return decrypted;
	}
}
