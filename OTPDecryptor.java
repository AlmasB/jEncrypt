public class OTPDecryptor extends Translator
{
  public String process(String text, String key)
	{
		String innerKey = getInnerKey(text, key);
		
		String decrypted = "";
		
		for (int i = 0; i < text.length(); i++)
		{
			decrypted += intToChar(charToInt(text.charAt(i)) - charToInt(innerKey.charAt(i)));
		}
		
		return decrypted;
	}
}
