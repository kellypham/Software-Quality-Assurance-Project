package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.Exceptions.*;
import AuctionrBack.Storage.*;

public class Advertise extends Command {

	private String[] args;
    	private ItemStorage itemStorage;
    	private UserStorage userStorage;
	
	private final int limitPrice = 1000;
	private final int limitItemName = 25;
	private final int limitNumOfDays = 100;
	
    public Advertise(String[] args){
		super(args);
    }

    public void Validate() throws MyException, ItemNotFoundException, UserNotFoundException{
    	//only accepted when logged in any type of account except standard-buy
    	String itemName = this.args[1];
    	Item item = this.itemStorage.GetByName(itemName);
    	String seller = item.GetSellerName();
    	
    	User user = this.userStorage.GetByName(seller);
    	UserType type = user.GetType();
    	
    	if (type.toString() != "BS") {
    		throw new MyException("Error: User must be an admin account");
    	}
    	
        //Check the maximum price for an item is 1000
    	String price = this.args[2];
    	if (Integer.parseInt(price) >= limitPrice){
    		throw new MyException("Error: The price of Item is greater than 999.99");
    	}
    	
    	//Check the maximum length of an item name is 25 characters
    	String name = this.args[1];
    	if (name.length() > limitItemName) {
    		throw new MyException("Error: The length of Item name is greater than 25 characters");
    	}
    	
    	//Check the maximum number of days to auction is 100
    	String numOfDays = this.args[3];
    	if (Integer.parseInt(numOfDays) > limitNumOfDays) {
    		throw new MyException("Error: The number of days to auction is greater than 100");
    	}
    }
    
    //Put up an item for auction
    public void Execute(){
    	Item item = new Item();
    	
    	//Args Variables
    	String itemName = this.args[1];
        String minBid = this.args[2];
        String numOfDays = this.args[3];
        
        
        //Execute
        item.SetName(itemName);
        item.SetHighestBid(Integer.parseInt(minBid));
        item.SetDaysRemaining(Integer.parseInt(numOfDays));
        
        this.itemStorage.Create(item);
       
    }
}
