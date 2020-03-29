package AuctionrBack.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Models.User;
import AuctionrBack.Storage.*;

public class TestCreate {

	@Test
	public void CreateCommandSuccessTest() throws Exception {
		String[] args = {"userTest", "AA", "1"};
		
		UserFileStorage storage = new UserFileStorage("users.txt");
		storage.Open();
		
		CreateCommand command = new CreateCommand(args, storage);

		command.Validate();
		command.Execute();

		User created = storage.GetByName(args[0]);
	}
	

	@Test(expected=Exception.class)
	public void CreateCommandUsernameIsTakenTest() throws Exception
	{
		String[] args = {"userone", "AA", "100"};
		
		UserFileStorage storage = new UserFileStorage("users.txt");
		storage.Open();
		
		CreateCommand command = new CreateCommand(args, storage);
		command.Validate();
	}
	
	
	@Test(expected=Exception.class)
	public void CreateCommandInvalidUserTypeTest() throws Exception
	{
		String[] args = {"userTest", "LO", "100"};
		
		UserFileStorage storage = new UserFileStorage("users.txt");
		storage.Open();
		
		CreateCommand command = new CreateCommand(args, storage);

		command.Validate();
	}
	
	
	@Test(expected=Exception.class)
	public void CreateCommandCreditOverLimitTest() throws Exception
	{
		String[] args = {"userTest", "AA", "99999999"};
		
		UserFileStorage storage = new UserFileStorage("users.txt");
		storage.Open();
		
		CreateCommand command = new CreateCommand(args, storage);

		command.Validate();
	}
	
	@Test(expected=Exception.class)
	public void CreateCommandCreditLessThanZeroTest() throws Exception
	{
		String[] args = {"User", "AA", "-1"};
		
		UserFileStorage storage = new UserFileStorage("users.txt");
		storage.Open();
		
		CreateCommand command = new CreateCommand(args, storage);

		command.Validate();
	}

}
