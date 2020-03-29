package AuctionrBack.Tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Models.Item;
import AuctionrBack.Models.User;
import AuctionrBack.Models.UserType;
import AuctionrBack.Storage.*;
import AuctionrBack.Storage.Exceptions.ItemNotFoundException;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;

public class TestBid
{
	UserFileStorage userStorage;
	ItemFileStorage itemStorage;

	@Test
	public void Bid() throws Exception
	{
		String[] args = {"item", "seller", "buyer", "10"};
		Bid command = CreateBid(args);

		command.Validate();
		command.Execute();

		Item item = itemStorage.Query("item", "seller");
		assertEquals(10, item.GetHigestBid());
		assertEquals("buyer", item.GetHighestBidderName());
	}

	@Test(expected=ItemNotFoundException.class)
	public void ItemNotFound() throws Exception
	{
		String[] args = {"item not found", "seller", "buyer", "10"};
		Bid command = CreateBid(args);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void BidTooLow() throws Exception
	{
		String[] args = {"item", "seller", "buyer", "8"};
		Bid command = CreateBid(args);

		command.Validate();
	}

	@Test(expected=UserNotFoundException.class)
	public void BuyerNotFound() throws Exception
	{
		String[] args = {"item", "seller", "UserDoesNotExist", "10"};
		Bid command = CreateBid(args);

		command.Validate();
	}

	@Test(expected=IllegalArgumentException.class)
	public void BuyerBalanceBelowBid() throws Exception
	{
		String[] args = {"item", "seller", "seller", "11"};
		Bid command = CreateBid(args);

		command.Validate();
	}

	@Test(expected=IllegalArgumentException.class)
	public void BuyerInvalidPermission() throws Exception
	{
		String[] args = {"item", "seller", "seller", "10"};
		Bid command = CreateBid(args);

		command.Validate();
	}

	private Bid CreateBid(String[] args) throws Exception
	{
		//Making sample data
		User seller = new User();
		seller.SetName("seller");
		seller.SetType(UserType.SELL_STANDARD);
		seller.SetCredit(10);

		User buyer = new User();
		buyer.SetName("buyer");
		buyer.SetType(UserType.BUY_STANDARD);
		buyer.SetCredit(10);

		Item item = new Item();
		item.SetName("item");
		item.SetSellerName(seller.GetName());
		item.SetHighestBid(8);
		item.SetDaysRemaining(10);

		userStorage = new UserFileStorage("users.txt");
		userStorage.Create(seller);
		userStorage.Create(buyer);

		itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Create(item);

		Bid command = new Bid(args, userStorage, itemStorage);
		return command;
	}
	
}