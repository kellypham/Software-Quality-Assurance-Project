package AuctionrBack.Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import AuctionrBack.Commands.Implementation.Refund;
import AuctionrBack.Models.User;
import AuctionrBack.Storage.*;

public class TestRefund
{
	@Test
	//Test that should succeed
	public void RefundSucessTest() throws Exception
	{
		String[] args = {"userone", "sellerone", "10"};		
		
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		
		Refund command = new Refund(args, userStorage);
		
		command.Validate();
		command.Execute();
	}

	//Tests that should thrown an exception and fail
	@Test(expected=Exception.class)
	public void RefundBuyerDoesNotExistTest() throws Exception
	{
		String[] args = {"User", "sellerone", "10"};	
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();

		Refund command = new Refund(args, userStorage);
		
		command.Validate();
	}
	
	
	@Test(expected=Exception.class)
	public void RefundSellerDoesNotExistTest() throws Exception
	{
		String[] args = {"userone", "sellerLOL", "10"};	
		
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		
		
		Refund command = new Refund(args, userStorage);
		
		
		
		command.Validate();
	}
	
	@Test(expected=Exception.class)
	public void RefundSellerDoesNotHaveCorrectCreditsTest() throws Exception
	{
		String[] args = {"userone", "sellerone", "99999999"};	
		
		UserFileStorage userStorage = new UserFileStorage("users.txt");
		userStorage.Open();
		
		Refund command = new Refund(args, userStorage);
	
		
		command.Validate();
	}
	
	
}