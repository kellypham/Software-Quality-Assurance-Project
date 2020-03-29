package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.*;

public class Bid extends Command
{
	private ItemStorage itemStorage;
    private UserStorage userStorage;
	
	private String itemName;
	private String sellerName;
	private String bidderName;
	private int amount;

	private User bidder;
	private Item item;

	public Bid(String[] args, UserStorage userStorage, ItemStorage itemStorage)
	{
		super(args);

		this.userStorage = userStorage;
		this.itemStorage = itemStorage;

		itemName = args[0];
		sellerName = args[1];
		bidderName = args[2];
		amount = Integer.parseInt(args[3]);
    }

	public void Validate() throws Exception
	{
		item = itemStorage.Query(itemName, sellerName);
    	bidder = this.userStorage.GetByName(bidderName);
    	
		if (bidder.GetType() == UserType.SELL_STANDARD)
		{
    		throw new IllegalArgumentException("Error: User must not a sell-standard account");
		}
		else if (bidder.GetCredit() < amount)
		{
			throw new IllegalArgumentException("Error: User balance is below bid");
		}
		else if (item.GetHigestBid() * 1.05 >= amount)
		{
    		throw new IllegalArgumentException("Error: The new bid must be at least 5% greater than the previous bid");
    	}
    }
    
    //make a bid on an item available for auction
	public void Execute() throws Exception
	{
		item.SetHighestBidderName(bidder.GetName());
		item.SetHighestBid(amount);
		
		itemStorage.Update(item);
    }
}
