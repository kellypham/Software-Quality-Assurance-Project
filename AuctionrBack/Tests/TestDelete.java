package AuctionrBack.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Models.User;
import AuctionrBack.Models.Item;
import AuctionrBack.Storage.*;
import AuctionrBack.Storage.Exceptions.*;
public class TestDelete {

	@Test
	public void DeleteCommandSuccessUserTest() throws Exception {
		String[] args = {"userone", "FS", "1"};
		
		//Opening UserFile storage and ItemFileStorage
		UserFileStorage storage = new UserFileStorage("users.txt");
		storage.Open();
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Open();
		
		
		DeleteCommand command = new DeleteCommand(args, storage, itemStorage);
		command.Validate();
		command.Execute();
		
		
		//Should fail
		//User created = storage.GetByName(args[0]);
		
	}
	
	@Test
	public void DeleteCommandSuccessItemTest() throws Exception {
		String[] args = {"userone", "FS", "1"};
		UserFileStorage storage = new UserFileStorage("users.txt");
		storage.Open();
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Open();

		//Delete Command
		DeleteCommand command = new DeleteCommand(args, storage, itemStorage);

		command.Validate();
		command.Execute();
		
		
		//Should fail
		//Item created = itemStorage.GetByName(args[0]);
	}
	

	@Test(expected=UserNotFoundException.class)
	public void DeleteCommandUsernotFound() throws Exception
	{
		String[] args = {"userTestNotDelete", "AA", "100"};
		UserFileStorage storage = new UserFileStorage("users.txt");
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");
		storage.Open();
		itemStorage.Open();
		DeleteCommand command = new DeleteCommand(args, storage, itemStorage);

		command.Validate();
	}
	
	

}
