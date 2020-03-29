package AuctionrBack.Tests;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;
import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Storage.*;

public class TestAddCredit
{
	@Test
	public void AddCreditSuccessTest() throws Exception
	{
		String[] args = {"userone", "100"};
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		
		AddCredit command = new AddCredit(args, userStorage);

		command.Validate();
		command.Execute();
		
	}


	@Test(expected=Exception.class)
	public void AddCreditUserNotFound() throws Exception
	{
		String[] args = {"User", "100"};
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		
		AddCredit command = new AddCredit(args, userStorage);

		command.Validate();
	}
	
	@Test(expected=Exception.class)
	public void AddCreditAmountOverLimitTest() throws Exception
	{
		String[] args = {"userone", "1001"};
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		
		AddCredit command = new AddCredit(args, userStorage);

		command.Validate();
	}
	
	@Test(expected=Exception.class)
	public void AddCreditAmountLessThanZeroTest() throws Exception
	{
		String[] args = {"userone", "0"};
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		
		AddCredit command = new AddCredit(args, userStorage);

		command.Validate();
	}
}