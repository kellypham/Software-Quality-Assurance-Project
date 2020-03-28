package AuctionrBack.Tests;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Models.User;
import AuctionrBack.Storage.*;

public class TestBid
{
	@Test
	public void BidSuccessTest() throws Exception
	{
		String[] args = {"User", "Item", "100"};
		UserStorage userStorage = new UserFileStorage("users.txt");
		ItemStorage itemStorage = new ItemFileStorage("items.txt");
		
		Bid command = new Bid(args, userStorage, itemStorage);

		command.Validate();
		command.Execute();
	}

	@Test(expected=IllegalArgumentException.class)
	public void BidItemNotFoundTest() throws Exception
	{
		String[] args = {"User", "itemone", "100"};
		UserStorage userStorage = new UserFileStorage("users.txt");
		ItemStorage itemStorage = new ItemFileStorage("items.txt");
		
		Bid command = new Bid(args, userStorage, itemStorage);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void BidLowerThanFivePercentageHighestTest() throws Exception
	{
		String[] args = {"User", "Item", "95"};
		UserStorage userStorage = new UserFileStorage("users.txt");
		ItemStorage itemStorage = new ItemFileStorage("items.txt");
		
		Bid command = new Bid(args, userStorage, itemStorage);

		command.Validate();
	}
}