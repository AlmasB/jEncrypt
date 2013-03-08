public class OTPEncryptor extends Translator 
{
  public String process(String text, String key)
	{
		String innerKey = getInnerKey(text, key);
		
		String encrypted = "";
		
		for (int i = 0; i < text.length(); i++)
		{
			encrypted += intToChar(charToInt(text.charAt(i)) + charToInt(innerKey.charAt(i)));
		}
		
		return encrypted;
	}
}
