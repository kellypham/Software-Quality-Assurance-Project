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
		Refund command = new Refund(args);

		command.Validate();
		command.Execute();
	}

	//Tests that should thrown an exception
	@Test(expected=IllegalArgumentException.class)
	public void RefundBuyerDoesNotExistTest() throws Exception
	{
		String[] args = {"User", "sellerone", "10"};		
		Refund command = new Refund(args);

		command.Validate();
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void RefundSellerDoesNotExistTest() throws Exception
	{
		String[] args = {"userone", "sellerone", "10"};		
		Refund command = new Refund(args);

		command.Validate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void RefundSellerDoesNotHaveCorrectCreditsTest() throws Exception
	{
		String[] args = {"userone", "sellerone", "99999999"};		
		Refund command = new Refund(args);
		command.Validate();
	}
	
	
}