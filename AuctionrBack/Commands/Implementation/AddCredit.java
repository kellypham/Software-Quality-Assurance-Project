package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.ItemStorage;

public class Advertise extends Command {

	private static final String[] args = null;
	
	private final int limitPrice = 1000;
	private final int limitItemName = 25;
	private final int limitNumOfDays = 100;
	
    public Advertise(String[] args){
		super(args);
    }

    public void Validate(){
        //Check the maximum price for an item is 1000
    	String price = this.args[2];
    	if (Integer.parseInt(price) >= 1000){
    		System.out.println("Error: The price of Item is greater than 999.99");
    	}
    	
    	//Check the maximum length of an item name is 25 characters
    	String name = this.args[1];
    	if (name.length() > 25) {
    		System.out.println("Error: The length of Item name is greater than 25 characters");
    	}
    	
    	//Check the maximum number of days to auction is 100
    	String numOfDays = this.args[3];
    	if (Integer.parseInt(numOfDays) > 100) {
    		System.out.println("Error: The number of days to auction is greater than 100");
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
        
        this.ItemStorage.Create(item);
       
    }
}
