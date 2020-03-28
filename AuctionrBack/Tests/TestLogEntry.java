package AuctionrBack.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import AuctionrBack.Input.LogEntry;

public class TestLogEntry {

	/**LogEntry Tests
	 * Testing LogEntry Functionality
	 * Methods String Transaction Code and String[] Arguments
	 * 
	 */
	
	@Test
	public void LogEntryTransactionCodeTest(){
		String code = "AA";
		String[] args = {"userOne", "hey", "hello"};
		LogEntry logEntry = new LogEntry(code, args);
		assertEquals(logEntry.TransactionCode(), "AA");
		
	}
	
	
	@Test
	public void LogEntryArgumentsTest(){
		String code = "AA";
		String[] args = {"userOne", "hey", "hello"};
		LogEntry logEntry = new LogEntry(code, args);
		assertArrayEquals(logEntry.Arguments(), args);
	}
	

}
