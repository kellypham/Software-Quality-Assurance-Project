package AuctionrBack.Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.AddCredit;
import AuctionrBack.Models.User;
import AuctionrBack.Storage.UserFileStorage;
import AuctionrBack.Storage.UserStorage;

public class TestAddCredit
{
	@Test
	public void Creates() throws Exception
	{
		String[] args = {"User", "AA", "1"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		AddCredit command = new AddCredit(args, storage);

		command.Validate();
		command.Execute();

		User created = storage.GetByName(args[0]);
	}


	@Test(expected=IllegalArgumentException.class)
	public void DoesntAllowOverMaxCredit()
	{
		String[] args = {"User", "AA", "99999999"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		AddCredit command = new AddCredit(args, storage);

		command.Validate();
	}
}