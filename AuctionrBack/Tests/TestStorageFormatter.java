package AuctionrBack.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import AuctionrBack.Storage.Formatting.StorageFormatter;

public class TestStorageFormatter {

	/**StorageFormatter Tests
	 * Testing StorageFormatter Functionality
	 * Methods Item Parse and String Parse 
	 */
	@Test
	//Testing Pad(String s, int size)
	public void StorageFormatterItemParseStringTest() {
		//Item used for testing the functionality Item Parse
		StorageFormatter formatter = new StorageFormatter();
		String returnValue = formatter.Pad("Nintendo Switch", 15);
		assertEquals(returnValue, "Nintendo Switch               ");
		
	}
	
	@Test
	//Testing Pad(int value, int size)
	public void StorageFormatterItemParseNumberTest(){
		//Item used for testing the functionality Item Parse
		StorageFormatter formatter = new StorageFormatter();
		String returnValue = formatter.Pad(15, 9);
		assertEquals(returnValue, "15         ");		
	}

}
