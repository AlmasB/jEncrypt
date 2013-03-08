public class Translator
{
  private final String ABC = "?abcdefghijklmnopqrstuvwxyz,.<>[]{}'ABCDEFGHIJKLMNOPQRSTUVWXYZ:;()-+=/*%!1234567890 ";
	
	private String sort(String line)
	{
		char[] key = line.toCharArray();
		String temp1 = "";
		String temp2 = "";
		
		for (int i = 0; i < key.length; i++)
		{
			if (i % 2 == 0)
				temp2 += key[i];
			else
				temp1 += key[i];
		}
		
		return temp1 + temp2;
	}
	
	public int charToInt(char c)
	{
		for (int i = 0; i < ABC.length(); i++)
		{
			if (ABC.charAt(i) == c)
				return i;
		}
		
		return -1;
	}
	
	public char intToChar(int a)
	{
		if (a > 0)
			a = a % ABC.length();
		else if (a < 0)
			a = ABC.length() + a;
		
		return ABC.charAt(a);
	}
	
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
	
	public String getInnerKey(String text, String key)
	{
		String innerKey = "";
		
		int i = 0;
		int j = 2;
		
		int sum = (int)(charToInt(key.charAt(i)) * Math.PI);
		
		while (innerKey.length() < text.length())
		{
			innerKey += intToChar(charToInt(key.charAt(i)) + sum + charToInt(key.charAt(i+1)) * (int)(j+Math.E+0.5));
			
			i++; j++; sum += charToInt(key.charAt(i)) + (int)(Math.PI * j);
			
			if (i == key.length()-1)
				i = 0;
		}
		
		for (int k = 0; k < 5; k ++)
		{
			innerKey = sort(innerKey);
		}
		
		return innerKey;
	}
}
