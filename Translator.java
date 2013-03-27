public class Translator
{
	/*
	 * Errors that could occur
	 */
	public static final String ERROR_NONE	    = "";
	public static final String ERROR_BAD_LENGTH = "Error 1: Message and key have to be of the same length!";
	public static final String ERROR_BAD_CHARS  = "Error 2: Your text/key contains not yet supported characters!";
	
	/*
	 * the cipher string. If you want to increase supported chars just add here.
	 */
	private final String ABC = "?abcdefghijklmnopqrstuvwxyz,.<>[]{}'ABCDEFGHIJKLMNOPQRSTUVWXYZ:;()-+=/*%!1234567890\"あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわゐゑを ";
	
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
	 * Checks if all chars in the text are convertable into values.
	 * If key is passed also checks whether length of key and text equal.
	 * @param str - message/text
	 * @param key - key
	 * @return - a string defining error nature or "" for no error
	 */
	
	public String validate(String str, String key)
	{
		for (int i = 0; i < str.length(); i++)
		{
			if (charToInt(str.charAt(i)) < 0)
				return ERROR_BAD_CHARS;
		}
		
		if (!key.isEmpty() && str.length() != key.length())
			return ERROR_BAD_LENGTH;
		
		return ERROR_NONE;
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
		
		return operator == '+' ? intToChar(char1 + char2) : intToChar(char1 - char2);
	}
	
	/**
	 * Generates a key, which is a string of random chars from the cipher string
	 * @param length - length of the key
	 * @return - key
	 */
	
	public String generateKey(int length)
	{
		String innerKey = "";
		
		while (innerKey.length() < length)
		{
			innerKey += intToChar((int)(Math.random() * (ABC.length() + 1)));
		}
		
		return innerKey;
	}
	
	/*
	 * The generated key is held here
	 */
	private String key = "";
	
	/**
	 * Encrypts any text passed to it
	 * @param text - text to encrypt
	 * @return - encrypted text or error message
	 */
	
	public String encrypt(final String text)
	{
		key = "";
		String mes = validate(text, "");
		
		if (!mes.isEmpty())
			return mes;
		
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
	
	/**
	 * Decrypts text with the given key
	 * @param text - text to decrypt
	 * @param key - key to use in decryption
	 * @return decrypted text or error message
	 */
	
	public String decrypt(final String text, final String decKey)
	{
		String mes = validate(text, decKey);
		
		if (!mes.isEmpty())
			return mes;

		String decrypted = "";
		
		for (int i = 0; i < text.length(); i++)
		{
			decrypted += combine(text.charAt(i), decKey.charAt(i), '-');
		}
		
		return decrypted;
	}
}
