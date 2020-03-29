package AuctionrBack.Tests;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Storage.*;

public class TestBid
{
	@Test
	public void BidSuccessTest() throws Exception
	{
		String[] args = {"test_item", "sellerone", "10"};
		
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Open();
		
		Bid command = new Bid(args, userStorage, itemStorage);

		command.Validate();
		command.Execute();
	}

	@Test(expected=Exception.class)
	public void BidItemNotFoundTest() throws Exception
	{
		String[] args = {"itemone", "userone", "100"};
		
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Open();
		
		Bid command = new Bid(args, userStorage, itemStorage);

		command.Validate();
	}
	
	@Test(expected=Exception.class)
	public void BidLowerThanFivePercentageHighestTest() throws Exception
	{
		String[] args = {"test_item", "userone", "1"};
		
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Open();
		
		Bid command = new Bid(args, userStorage, itemStorage);

		command.Validate();
	}
}