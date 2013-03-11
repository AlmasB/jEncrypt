public class Translator
{
	/*
	 * the cipher string. If you want to increase supported chars just add here.
	 */
	private final String ABC = "?abcdefghijklmnopqrstuvwxyz,.<>[]{}'ABCDEFGHIJKLMNOPQRSTUVWXYZ:;()-+=/*%!1234567890 ";
	
	/**
	 * Looks up an int value of the char from the cipher string
	 * @param c - char which value is being searched
	 * @return - an int value of the char
	 */
	
	private int charToInt(char c)
	{
		for (int i = 0; i < ABC.length(); i++)
		{
			if (ABC.charAt(i) == c)
				return i;
		}
		
		return -1;
	}
	
	/**
	 * Returns a char from the cipher string with the passed index
	 * @param a - index
	 * @return - char at that index
	 */
	
	private char intToChar(int a)
	{
		if (a >= ABC.length())
			a %= ABC.length();
		else if (a < 0)
			a += ABC.length();
		
		return ABC.charAt(a);
	}
	
	/**
	 * Checks whether all chars from the string are convertable
	 * @param str - checked string
	 * @return - true if ALL chars exist in the cipher string, false otherwise
	 */
	
	public boolean validate(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
			if (charToInt(str.charAt(i)) < 0)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Transforms two chars into one depending on the operator passed.
	 * @param a - first char
	 * @param b - second char
	 * @param operator - arithmetical operator used in transformation
	 * @return combined char
	 */
	
	public char combine(char a, char b, char operator)
	{
		int char1 = charToInt(a);
		int char2 = charToInt(b);
		
		boolean plus = operator == '+';
		
		return plus ? intToChar(char1 + char2) : intToChar(char1 - char2);
	}
	
	/**
	 * Generates a key, which is a string of random chars from the cipher string
	 * @param length - length of the key
	 * @return - key
	 */
	
	public String generateKey(int length)
	{
		String key = "";
		
		while (key.length() < length)
		{
			int a = (int)(Math.random() * (ABC.length() + 1));
			key += intToChar(a);
		}
		
		return key;
	}
}
