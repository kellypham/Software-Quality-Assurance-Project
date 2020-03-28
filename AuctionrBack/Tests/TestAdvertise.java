package AuctionrBack.Tests;

import org.junit.Test;

import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Models.User;
import AuctionrBack.Storage.*;

public class TestAdvertise
{
	@Test
	public void AdvertiseSuccessTest() throws Exception
	{
		String[] args = {"Item", "100", "10"};
		UserStorage userStorage = new UserFileStorage("users.txt");
		ItemStorage itemStorage = new ItemFileStorage("items.txt");
		
		Advertise command = new Advertise(args, userStorage, itemStorage);

		command.Validate();
		command.Execute();
	}

	@Test(expected=IllegalArgumentException.class)
	public void AdvertisePriceAboveLimitTest() throws Exception
	{
		String[] args = {"Item", "1000", "10"};
		UserStorage userStorage = new UserFileStorage("users.txt");
		ItemStorage itemStorage = new ItemFileStorage("items.txt");
		
		Advertise command = new Advertise(args, userStorage, itemStorage);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AdvertiseDaysOverLimitTest() throws Exception
	{
		String[] args = {"Item", "100", "101"};
		UserStorage userStorage = new UserFileStorage("users.txt");
		ItemStorage itemStorage = new ItemFileStorage("items.txt");
		
		Advertise command = new Advertise(args, userStorage, itemStorage);

		command.Validate();
	}
}