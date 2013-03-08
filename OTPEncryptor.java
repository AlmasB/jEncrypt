public class OTPEncryptor extends Translator 
{
	public String process(String text, String key)
	{
		String innerKey = getInnerKey(text, key);
		
		String encrypted = "";
		
		for (int i = 0; i < text.length(); i++)
		{
			encrypted += combine(text.charAt(i), innerKey.charAt(i), '+');
		}
		
		return encrypted;
	}
}
