package AuctionrBack.Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;
import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Models.User;
import AuctionrBack.Storage.*;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;

public class TestAddCredit
{
	UserFileStorage storage;

	@Test
	public void AddCredit() throws Exception
	{
		final String USER_NAME="user";
		final int ADD_CREDIT = 100;

		String[] args = {USER_NAME, "AA", "" + ADD_CREDIT};
		AddCredit command = CreateCommand(args);

		command.Validate();
		command.Execute();

		User afterUpdate = storage.GetByName(USER_NAME);
		assertEquals("Credit was not added to the user account", ADD_CREDIT, afterUpdate.GetCredit());
	}


	@Test(expected=UserNotFoundException.class)
	public void UserNotFound() throws Exception
	{
		String[] args = {"NotFound", "AA", "100"};
		AddCredit command = CreateCommand(args);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AmountOverLimit() throws Exception
	{
		String[] args = {"user", "AA", "1001"};
		AddCredit command = CreateCommand(args);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AmountBelowMinimum() throws Exception
	{
		String[] args = {"user", "AA", "-1"};
		AddCredit command = CreateCommand(args);

		command.Validate();
	}

	private AddCredit CreateCommand(String[] args)
	{
		User user = new User();
		user.SetName("user");
		user.SetCredit(0);

		storage = new UserFileStorage("users.txt");
		storage.Create(user);

		AddCredit command = new AddCredit(args, storage);
		return command;
	}
}