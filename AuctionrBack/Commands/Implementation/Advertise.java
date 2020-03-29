package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.*;
import AuctionrBack.Storage.Exceptions.DuplicateItemException;
import AuctionrBack.Storage.Exceptions.ItemNotFoundException;

public class Advertise extends Command
{
	private final int MAX_PRICE = 1000;
	private final int NAME_LENGTH_LIMIT = 25;
	private final int MAX_DAYS = 100;

    private ItemStorage itemStorage;
    private UserStorage userStorage;
	
	private String itemName;
	private String sellerName;
	private int daysToAuction;
	private int initialPrice;

	private User seller;
	
	public Advertise(String[] args, UserStorage userStorage, ItemStorage itemStorage)
	{
		super(args);
		
		this.userStorage = userStorage;
		this.itemStorage = itemStorage;

		itemName = args[0];
		sellerName = args[1];
		daysToAuction = Integer.parseInt(args[2]);
		initialPrice = Integer.parseInt(args[3]);
    }

	public void Validate() throws Exception
	{
		seller = userStorage.GetByName(sellerName);
    	
		if (seller.GetType() == UserType.BUY_STANDARD)
		{
    		throw new IllegalArgumentException("Error: User must not be a buy-standard account");
		}
		else if (initialPrice >= MAX_PRICE)
		{
    		throw new IllegalArgumentException("Error: The price of Item is greater than 999.99");
    	}
		else if (itemName.length() > NAME_LENGTH_LIMIT)
		{
    		throw new IllegalArgumentException("Error: The length of Item name is greater than "+NAME_LENGTH_LIMIT+" characters");
    	}
		else if (daysToAuction > MAX_DAYS)
		{
    		throw new IllegalArgumentException("Error: The number of days to auction is greater than 100");
    	}
    }
    
    //Put up an item for auction
	public void Execute() throws Exception
	{
		Item item = new Item();
		
		item.SetName(itemName);
		item.SetSellerName(sellerName);
		item.SetHighestBidderName(sellerName);
		item.SetDaysRemaining(daysToAuction);
		item.SetHighestBid(initialPrice);
        
        itemStorage.Create(item);
    }
}
