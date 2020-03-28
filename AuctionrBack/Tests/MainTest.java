package AuctionrBack.Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.Assert;
import AuctionrBack.Commands.Command;
import AuctionrBack.Commands.CommandFactory;
import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Input.*;
import AuctionrBack.Models.Item;
import AuctionrBack.Models.User;
import AuctionrBack.Models.UserType;
import AuctionrBack.Storage.*;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;
import AuctionrBack.Storage.Formatting.StorageFormatter;



public class MainTest {
	
	// Variables used for testing
	private Item item = new Item();
	private User user = new User();
	private DailyLogFile log = new DailyLogFile("log.txt");
	private static ItemFileStorage itemStorage;
	
	/**Item Tests
	 * Testing Setters and Getters for Items
	 * Combining them into one so we can test Set and Get Together
	 */
	/*
	@Test
	public void SetGetItemNameTest() {
		item.SetName("Nintendo Switch");
		assertEquals(item.GetName(), "Nintendo Switch");
	}
	
	@Test
	public void SetGetItemSellerNameTest() {
		item.SetSellerName("Bob");
		assertEquals(item.GetSellerName(), "Bob");
	}
	
	
	@Test
	public void SetGetItemHighestBidderNameTest() {
		item.SetHighestBidderName("Sam");
		assertEquals(item.GetHighestBidderName(), "Sam");
	}
	
	@Test
	public void SetGetItemGetDaysRemainingTest() {
		item.SetDaysRemaining(20);
		assertEquals(item.GetDaysRemaining(), 20);
	}
	
	@Test
	public void SetGetItemGetHighestBid() {
		item.SetHighestBid(199);
		assertEquals(item.GetHigestBid(), 199);
	}
	
	@Test
	public void SetGetItemIsOver() {
		item.SetDaysRemaining(20);
		assertEquals(item.IsOver(), false);
	}
	*/
	
	
	/**Users Test
	 * Testing Setters and Getters for Users
	 * Combining them into one so we can test Set and Get Together
	 */
	/*
	@Test
	public void SetGetUserNameTest() {
		user.SetName("Bob");
		assertEquals(user.GetName(), "Bob");
	}
	
	@Test
	public void SetGetUserTypeTest() {
		user.SetType(UserType.ADMIN);
		assertEquals(user.GetType(), UserType.ADMIN);
	}
	
	@Test
	public void SetGetUserCreditTest() {
		user.SetCredit(1000);
		assertEquals(user.GetCredit(), 1000);
	}
	
	@Test
	public void SetGetUserIsAdminTest() {
		user.SetType(UserType.ADMIN);
		assertEquals(user.IsAdmin(), true);
	}
	*/
	
	/**StorageFormatter Tests
	 * Testing StorageFormatter Functionality
	 * Methods Item Parse and String Parse 
	 */
	/*
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
	*/
	
	/**LogEntry Tests
	 * Testing LogEntry Functionality
	 * Methods String Transaction Code and String[] Arguments
	 * 
	 */
	
	/*
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
	*/
	
	
	/**DailyLogFile Tests
	 * Testing Initialize() NextItem() IsEmpty()
	 * Methods String Transaction Code and String[] Arguments
	 * 
	 */
	/*
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
	
	*/
	
	
	
	
	
}
