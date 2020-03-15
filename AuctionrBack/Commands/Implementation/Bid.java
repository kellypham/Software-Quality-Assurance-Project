package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.*;
import AuctionrBack.Storage.Exceptions.*;

public class Bid extends Command {
	
	private String[] args;
    private ItemStorage itemStorage;
    private UserStorage userStorage;
	

    public Bid(String[] args){
		super(args);
    }

    public void Validate() throws Exception{
    	//only accepted when logged in any type of account except standard-sell
    	String userName = this.args[2];
    	User user = this.userStorage.GetByName(userName);
    	UserType type = user.GetType();
    	
    	if (type.toString() == "SS") {
    		throw new Exception("Error: User must not a sell-standard account");
    	}
    	
    	//item name must be an existing item with the exception
    	
    	String itemName = this.args[1];
    	Item item = this.itemStorage.GetByName(itemName);
    	int oldbid = item.GetHigestBid();
    	String newbid = this.args[3];
    	
    	//new bid must be greater than the previous highest bid
    	if (oldbid >= Integer.parseInt(newbid)) {
    		throw new Exception("Error: The new bid must be greater than the perious bid");
    	}
    	
    	//new bid must be at least 5% higher than the previous highest bid
    	if (Integer.parseInt(newbid) < oldbid*0.5) {
    		throw new Exception("Error: The new bid must be at least 5% higher than the previous highest bid");
    	}
    	
    }
    
    //make a bid on an item available for auction
    public void Execute() throws ItemNotFoundException{
    	//Args Variable
        String itemName = this.args[1];
        String userName = this.args[2];
        String newbid = this.args[3];
        
        //Finding the Item
        Item item = this.itemStorage.GetByName(itemName);

        //Execute the variable
        item.SetHighestBid(Integer.parseInt(newbid));
    }

}
