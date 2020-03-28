package AuctionrBack.Tests;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Models.User;
import AuctionrBack.Storage.*;

public class TestAddCredit
{
	@Test
	public void AddCreditSuccessTest() throws Exception
	{
		String[] args = {"User", "AA", "100"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		AddCredit command = new AddCredit(args, storage);

		command.Validate();
		command.Execute();

		User created = storage.GetByName(args[0]);
	}


	@Test(expected=IllegalArgumentException.class)
	public void AddCreditUserNotFound() throws Exception
	{
		String[] args = {"userone", "AA", "100"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		AddCredit command = new AddCredit(args, storage);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AddCreditAmountOverLimitTest() throws Exception
	{
		String[] args = {"User", "AA", "1001"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		AddCredit command = new AddCredit(args, storage);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AddCreditAmountLessThanZeroTest() throws Exception
	{
		String[] args = {"User", "AA", "-1"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		AddCredit command = new AddCredit(args, storage);

		command.Validate();
	}
}