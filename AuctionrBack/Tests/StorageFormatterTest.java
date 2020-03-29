package AuctionrBack.Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import AuctionrBack.Storage.Formatting.StorageFormatter;

public class StorageFormatterTest
{
	@Test
	public void PadString()
	{
		final int NEW_LENGTH = 10;
		String original = "Str";

		StorageFormatter formatter = new StorageFormatter();
		String modified = formatter.Pad(original, NEW_LENGTH);
		
		assertEquals(NEW_LENGTH, modified.length());
	}

	@Test
	public void PadInt()
	{
		final int NEW_LENGTH = 10;
		int original = 10;

		StorageFormatter formatter = new StorageFormatter();
		String modified = formatter.Pad(original, NEW_LENGTH);
		
		assertEquals(NEW_LENGTH, modified.length());
	}
}