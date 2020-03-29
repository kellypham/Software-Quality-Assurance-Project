package AuctionrBack.Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Models.Item;
import AuctionrBack.Models.User;
import AuctionrBack.Models.UserType;
import AuctionrBack.Storage.*;
import AuctionrBack.Storage.Exceptions.DuplicateItemException;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;

public class TestAdvertise
{
	private UserStorage userStorage;
	private ItemStorage itemStorage;

	@Test
	public void Advertise() throws Exception
	{
		String[] args = {"item", "seller", "10", "10"};
		Advertise command = CreateCommand(args);

		command.Validate();
		command.Execute();

		Item item = itemStorage.Query("item", "seller");
		assertEquals("item", item.GetName());
		assertEquals("seller", item.GetSellerName());
		assertEquals("seller", item.GetHighestBidderName());
		assertEquals(10, item.GetHigestBid());
		assertEquals(10, item.GetDaysRemaining());
	}

	@Test(expected=IllegalArgumentException.class)
	public void ItemNameTooLong() throws Exception
	{
		String[] args = {"ThisNameIsOneAboveTheLimit", "seller", "10", "10"};
		Advertise command = CreateCommand(args);

		command.Validate();
	}

	@Test(expected=IllegalArgumentException.class)
	public void SellerTypeNotPermitted() throws Exception
	{
		String[] args = {"item", "buyer", "10", "10"};
		Advertise command = CreateCommand(args);

		command.Validate();
	}

	@Test(expected=IllegalArgumentException.class)
	public void PriceAboveLimit() throws Exception
	{
		String[] args = {"item", "seller", "10", "10000"};
		Advertise command = CreateCommand(args);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void DaysOverLimit() throws Exception
	{
		String[] args = {"item", "seller", "101", "10"};
		Advertise command = CreateCommand(args);

		command.Validate();
	}

	@Test(expected=UserNotFoundException.class)
	public void SellerNotFound() throws Exception
	{
		String[] args = {"item", "NotFound", "10", "10"};
		Advertise command = CreateCommand(args);

		command.Validate();
	}

	@Test(expected=DuplicateItemException.class)
	public void ItemAlreadyForSale() throws Exception
	{
		String[] args = {"DuplicateItem", "seller", "10", "10"};
		Advertise command = CreateCommand(args);

		command.Validate();
		command.Execute();
	}

	public Advertise CreateCommand(String[] args) throws Exception
	{
		User seller = new User();
		seller.SetName("seller");

		User buyer = new User();
		buyer.SetName("buyer");
		buyer.SetType(UserType.BUY_STANDARD);

		Item item = new Item();
		item.SetName("DuplicateItem");
		item.SetSellerName(seller.GetName());
		item.SetHighestBid(item.GetHigestBid());

		userStorage = new UserFileStorage("users.txt");
		userStorage.Create(seller);
		userStorage.Create(buyer);

		itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Create(item);
		
		Advertise command = new Advertise(args, userStorage, itemStorage);
		return command;
	}
}