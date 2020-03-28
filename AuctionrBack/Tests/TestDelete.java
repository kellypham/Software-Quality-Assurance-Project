package AuctionrBack.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.AddCredit;
import AuctionrBack.Commands.Implementation.CreateCommand;
import AuctionrBack.Commands.Implementation.DeleteCommand;
import AuctionrBack.Models.User;
import AuctionrBack.Models.Item;
import AuctionrBack.Storage.UserFileStorage;
import AuctionrBack.Storage.UserStorage;
import AuctionrBack.Storage.ItemFileStorage;
import AuctionrBack.Storage.ItemStorage;
public class TestDelete {

	@Test
	public void DeleteCommandSuccessUserTest() throws Exception {
		String[] args = {"userone", "FS", "1"};
		UserStorage storage = new UserFileStorage("users.txt");
		ItemStorage itemStorage = new ItemFileStorage("items.txt");
		DeleteCommand command = new DeleteCommand(args, storage, itemStorage);

		command.Validate();
		command.Execute();
		
		//Should fail
		User created = storage.GetByName(args[0]);
		
	}
	
	@Test
	public void DeleteCommandSuccessItemTest() throws Exception {
		String[] args = {"userone", "FS", "1"};
		UserStorage storage = new UserFileStorage("users.txt");
		ItemStorage itemStorage = new ItemFileStorage("items.txt");
		DeleteCommand command = new DeleteCommand(args, storage, itemStorage);

		command.Validate();
		command.Execute();
		
		
		//Should fail
		Item created = itemStorage.GetByName(args[0]);
		
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void DeleteCommandUsernotFound() throws Exception
	{
		String[] args = {"userone", "AA", "100"};
		UserStorage storage = new UserFileStorage("users.txt");
		ItemStorage itemStorage = new ItemFileStorage("items.txt");
		DeleteCommand command = new DeleteCommand(args, storage, itemStorage);

		command.Validate();
	}
	
	

}
