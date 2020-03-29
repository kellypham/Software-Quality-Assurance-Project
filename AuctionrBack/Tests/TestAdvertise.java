package AuctionrBack.Tests;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Storage.*;

public class TestAdvertise
{
	@Test
	public void AdvertiseSuccessTest() throws Exception
	{
		String[] args = {"test_item", "100", "10"};
		
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Open();
		
		Advertise command = new Advertise(args, userStorage, itemStorage);

		command.Validate();
		command.Execute();

	}

	@Test(expected=Exception.class)
	public void AdvertisePriceAboveLimitTest() throws Exception
	{
		String[] args = {"test_item", "1000", "10"};
		
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Open();
		
		Advertise command = new Advertise(args, userStorage, itemStorage);

		command.Validate();
	}
	
	@Test(expected=Exception.class)
	public void AdvertiseDaysOverLimitTest() throws Exception
	{
		String[] args = {"test_item", "100", "101"};
		
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");
		itemStorage.Open();
		
		Advertise command = new Advertise(args, userStorage, itemStorage);

		command.Validate();
	}
}