public class Translator
{
	/*
	 * the cipher string. If you want to increase supported chars just add here.
	 */
	private final String ABC = "?abcdefghijklmnopqrstuvwxyz,.<>[]{}'ABCDEFGHIJKLMNOPQRSTUVWXYZ:;()-+=/*%!1234567890 ";
	
	/**
	 * Sorts the string in a way that all odd chars go to beginning
	 * and all even chars are added afterwards
	 * @param line - the string to be sorted
	 * @return - sorted string
	 */
	
	private String sort(String line)
	{
		String temp1 = "";
		String temp2 = "";
		
		for (int i = 0; i < line.length(); i++)
		{
			if (i % 2 == 0)
				temp2 += line.charAt(i);
			else
				temp1 += line.charAt(i);
		}
		
		return temp1 + temp2;
	}
	
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
		if (a > 0)
			a = a % ABC.length();
		else if (a < 0)
			a = ABC.length() + a;
		
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
		int result = 0;
		
		switch (operator)
		{
			case '+':
				result = char1 + char2;
				break;
			case '-':
				result = char1 - char2;
				break;
		}
		
		return intToChar(result);
	}
	
	/**
	 * Generates another key which is not seen by the user.
	 * It's this key that's used for encryption/decryption.
	 * However, it can only be generated from the original key.
	 * So if the user entered key isn't the same then you get a different decryption.
	 * @param length - length of the message
	 * @param key - original key that the user entered
	 * @return - generated key
	 */
	
	public String getInnerKey(int length, String key)
	{
		/*
		 * The cipher value, sort of key factor, can change to generate a different key
		 * Preferably an irrational number
		 * Will make dynamic in the following versions
		 */
		final double VAL = Math.PI;
				
		String innerKey = "";
		
		int i = 0;
		int j = 2;
		
		//Will change the algorithm later to make more complex but easy to read
		
		int factor = (int)(charToInt(key.charAt(i)) * VAL);
		
		while (innerKey.length() < length)
		{
			int char1 = charToInt(key.charAt(i));
			int char2 =	charToInt(key.charAt(i + 1));
			
			innerKey += intToChar(factor + char1 + (int)(char2 * (j + VAL)));

			factor += char1 + (int)(j * VAL);
			
			i++; j++;
			
			if (i == key.length() - 1)
				i = 0;
		}
		
		for (int k = 0; k < 5; k ++)
		{
			innerKey = sort(innerKey);
		}
		
		return innerKey;
	}
}
