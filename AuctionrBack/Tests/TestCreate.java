package AuctionrBack.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.AddCredit;
import AuctionrBack.Commands.Implementation.CreateCommand;
import AuctionrBack.Models.User;
import AuctionrBack.Storage.UserFileStorage;
import AuctionrBack.Storage.UserStorage;

public class TestCreate {

	@Test
	public void CreateCommandSuccessTest() throws Exception {
		String[] args = {"userTest", "AA", "1"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		CreateCommand command = new CreateCommand(args, storage);

		command.Validate();
		command.Execute();

		User created = storage.GetByName(args[0]);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void CreateCommandUsernameIsTakenTest() throws Exception
	{
		String[] args = {"userone", "AA", "100"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		CreateCommand command = new CreateCommand(args, storage);

		command.Validate();
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void CreateCommandInvalidUserTypeTest() throws Exception
	{
		String[] args = {"userTest", "LO", "100"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		CreateCommand command = new CreateCommand(args, storage);

		command.Validate();
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void CreateCommandCreditOverLimitTest() throws Exception
	{
		String[] args = {"userTest", "AA", "99999999"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		CreateCommand command = new CreateCommand(args, storage);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CreateCommandCreditLessThanZeroTest() throws Exception
	{
		String[] args = {"User", "AA", "-1"};
		UserStorage storage = new UserFileStorage("users.txt");
		
		CreateCommand command = new CreateCommand(args, storage);

		command.Validate();
	}

}
