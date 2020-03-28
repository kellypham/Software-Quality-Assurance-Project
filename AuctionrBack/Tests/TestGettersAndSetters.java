package AuctionrBack.Tests;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.Assert;
import AuctionrBack.Commands.Command;
import AuctionrBack.Commands.CommandFactory;
import AuctionrBack.Commands.Implementation.*;
import AuctionrBack.Input.*;
import AuctionrBack.Models.Item;
import AuctionrBack.Models.User;
import AuctionrBack.Models.UserType;
import AuctionrBack.Storage.*;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;
import AuctionrBack.Storage.Formatting.StorageFormatter;


public class TestGettersAndSetters {
	
	// Variables used for testing
	private Item item = new Item();
	private User user = new User();
	
	
	/**Item Tests
	 * Testing Setters and Getters for Items
	 * Combining them into one so we can test Set and Get Together
	 */
	@Test
	public void SetGetItemNameTest() {
		item.SetName("Nintendo Switch");
		assertEquals(item.GetName(), "Nintendo Switch");
	}
	
	@Test
	public void SetGetItemSellerNameTest() {
		item.SetSellerName("Bob");
		assertEquals(item.GetSellerName(), "Bob");
	}
	
	
	@Test
	public void SetGetItemHighestBidderNameTest() {
		item.SetHighestBidderName("Sam");
		assertEquals(item.GetHighestBidderName(), "Sam");
	}
	
	@Test
	public void SetGetItemGetDaysRemainingTest() {
		item.SetDaysRemaining(20);
		assertEquals(item.GetDaysRemaining(), 20);
	}
	
	@Test
	public void SetGetItemGetHighestBid() {
		item.SetHighestBid(199);
		assertEquals(item.GetHigestBid(), 199);
	}
	
	@Test
	public void SetGetItemIsOver() {
		item.SetDaysRemaining(20);
		assertEquals(item.IsOver(), false);
	}
	
	/**Users Test
	 * Testing Setters and Getters for Users
	 * Combining them into one so we can test Set and Get Together
	 */
	@Test
	public void SetGetUserNameTest() {
		user.SetName("Bob");
		assertEquals(user.GetName(), "Bob");
	}
	
	@Test
	public void SetGetUserTypeTest() {
		user.SetType(UserType.ADMIN);
		assertEquals(user.GetType(), UserType.ADMIN);
	}
	
	@Test
	public void SetGetUserCreditTest() {
		user.SetCredit(1000);
		assertEquals(user.GetCredit(), 1000);
	}
	
	@Test
	public void SetGetUserIsAdminTest() {
		user.SetType(UserType.ADMIN);
		assertEquals(user.IsAdmin(), true);
	}

}
