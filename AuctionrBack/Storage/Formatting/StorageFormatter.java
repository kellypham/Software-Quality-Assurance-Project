package AuctionrBack.Storage.Formatting;

public class StorageFormatter
{
	public String Pad(String s, int size)
	{
		int finalSize = s.length() + size;

		for (int i = s.length(); i < finalSize; i++)
		{
			s += ' ';
		}

		return s;
	}

	public String Pad(int value, int size)
	{
		String valueString = "" + value;
		return Pad(valueString, size);
	}
}