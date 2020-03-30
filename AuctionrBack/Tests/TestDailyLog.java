package AuctionrBack.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import AuctionrBack.Input.DailyLogFile;
import AuctionrBack.Input.LogEntry;

public class TestDailyLog {

	/**DailyLogFile Tests
	 * Testing Initialize() NextItem() IsEmpty()
	 * Methods String Transaction Code and String[] Arguments
	 * 
	 */
	private DailyLogFile log = new DailyLogFile("log.txt");
	
	@Test
	public void DailyLogFileInitializeTest() throws Exception
	{
		log.Initialize();
	}
	
	
	@Test
	public void DailyLogFileNextItemTest() throws Exception
	{
		String expectedCode = "06";
		String[] expectedArguments = {"userone", "FS", "1"};

		log.Initialize();

		LogEntry result = log.NextItem();
		assertEquals(expectedCode, result.TransactionCode());
		assertArrayEquals(expectedArguments, result.Arguments());
		
	}

	
	@Test
	public void DailyLogFileIsEmptyTestTrue()
	{
		assertTrue(log.IsEmpty());
	}
	
	@Test
	public void DailyLogFileIsEmptyTestFalse() throws Exception
	{
		log.Initialize();
		assertFalse(log.IsEmpty());
	}

}
