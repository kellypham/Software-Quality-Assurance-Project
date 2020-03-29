package AuctionrBack.Models;

/**
 * Class for storing Items that are up for sale,
 *  and the details of the auction on that item
 */
public class Item
{
	/** Name of the item */
    private String name = "";
    /** Name of the item's seller */
    private String sellerName = "";
    /** Name of the item's highest bidder */
    private String highestBidderName = "";
    /** Number of days remaining to bid on this item */
    private int daysRemaining;
    /** Highest bid on this item */
    private int highestBid;

    //Getters and Setters for Item Class

    /**
    * Accessor for the Item's name
	* @return Item's name
	*/
	public String GetName()
	{
        return this.name;
    }
    /**
	* Mutator for Item's name
	* @param name New name of the item
	*/
	public void SetName(String name)
	{
        this.name = name;
    }
    /**
	* Accessor for the name of this item's seller
	* @return Item's seller name
	*/
	public String GetSellerName()
	{
        return this.sellerName;
    }
	
    /**
	* Mutator for Item's seller's name
	* @param name New name of the seller of the item
	 */
	public void SetSellerName(String sellerName)
	{
        this.sellerName = sellerName;
    }
    /**
	* Accessor for the name of the highest bidder on this item
	* @return Item's highest bidder's name
	*/
	public String GetHighestBidderName()
	{
        return this.highestBidderName;
    }
    /**
	* Mutator for name of the highest bidder on the Item
	* @param name Name of the new highest bidder on the Item
	*/
	public void SetHighestBidderName(String highestBidderName)
	{
        this.highestBidderName = highestBidderName;
    }
    /**
	* Accessor for the amount of days remaining to bid on this item
	* @return Days remaining to bid on this item
	*/
	public int GetDaysRemaining()
	{
        return this.daysRemaining;
    }
    /**
	* Mutator for Item's days remaining on this auction
	* @param name New amount of days remaining
	*/
	public void SetDaysRemaining(int days)
	{
        this.daysRemaining = days;
    }
    /**
	* Accessor for the highest bid on this item
	* @return Item's highest bid
	*/
	public int GetHigestBid()
	{
        return this.highestBid;
    }
    /**
	* Mutator for the highest bid on the Item
	* @param name New highest bid
	*/
	public void SetHighestBid(int bid)
	{
        this.highestBid = bid;
	}
	
	/**
	 * Returns whether this auction is over
	 * @return True if the item's days remaining are < 1, false otherwise
	 */
	public boolean IsOver()
	{
		return daysRemaining < 1;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || this.getClass() != obj.getClass())
		{
			return false;
		}

		Item item = (Item)obj;
		
		return GetName().equals(item.GetName())
				&& GetSellerName().equals((item.GetSellerName()))
				&& GetHighestBidderName().equals((item.GetHighestBidderName()))
				&& GetDaysRemaining() == item.GetDaysRemaining()
				&& GetHigestBid() == item.GetHigestBid();
	}
}