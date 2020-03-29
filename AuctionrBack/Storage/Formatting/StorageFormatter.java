package AuctionrBack.Storage.Formatting;

/**
 * Utility class for formatting fields before outputting them to long term storage
 */
public class StorageFormatter
{
	/**
	 * Pads a string with spaces on the end until its length is equal to size
	 * @param s String to pad
	 * @param size Size to pad the string up to
	 * @return The string with spaces added on the end
	 */
	public String Pad(String s, int size)
	{
		int sizeDifference = size - s.length();

		for (int i = 0; i < sizeDifference; i++)
		{
			s += ' ';
		}

		return s;
	}

	/**
	 * Pads an integer into a string with spaces added on the end until
	 * its length is equal to size
	 * @param value Value to parse
	 * @param size Size to pad the value's length up to
	 * @return The integer as a string, with spaces on the end
	 */
	public String Pad(int value, int size)
	{
		String valueString = "" + value;
		return Pad(valueString, size);
	}
}