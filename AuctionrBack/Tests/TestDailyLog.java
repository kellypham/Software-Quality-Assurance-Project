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
	public void DailyLogFileInitializeTest(){
		try{
			log.Initialize();
		}
		catch(Exception ex){
			//Throws an exception
		}
	}
	
	
	@Test
	public void DailyLogFileNextItemTest(){
		try{
			log.Initialize();
		}
		catch(Exception ex){
			//Throws an exception
			
		}
		String[] args = {"userone", "FS", "1"};
		LogEntry result = log.NextItem();
		assertArrayEquals(result.Arguments(), args);
		
	}

	
	@Test
	public void DailyLogFileIsEmptyTestTrue(){
		assertTrue(log.IsEmpty());
	}
	
	@Test
	public void DailyLogFileIsEmptyTestFalse(){
		try{
			log.Initialize();
		}
		catch(Exception ex){
			
		}
		assertFalse(log.IsEmpty());
	}

}
