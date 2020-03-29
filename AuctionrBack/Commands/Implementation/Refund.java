package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.UserFileStorage;
import AuctionrBack.Storage.UserStorage;

//Class Refund
public class Refund extends Command {
    private String[] args;
    private UserStorage userStorage;
    private String buyer;
    private String seller;
    private int credits;
    //Constructor
    public Refund(String[] args, UserStorage userStorage){
		  super(args);
		  this.args = args;
		  buyer = this.args[0];
		  seller = this.args[1];
		  credits = Integer.parseInt(this.args[2]);
		  this.userStorage = userStorage;
		  
    }

    //Valdidating the Refund arguments
    public void Validate() throws Exception{
    	
    	
        //Validiating that the number of arguments is correct
        if(this.args.length != 3){
          throw new Exception("Error: The arguments don't have the required length ");
        }       
        
        //checking if buyer's and user's are a valid user will throw an exception
        User buyerUser = this.userStorage.GetByName(buyer);
        User sellerUser = this.userStorage.GetByName(seller);
        
        if(sellerUser.GetCredit() < credits){
        	throw new Exception("Error: Don't have enough credits to refund");
        }
        
        
    }
    //Executing the Refund arguments
    public void Execute() throws Exception{
      //Getting the Strings 
      String buyerString = this.args[0];
      String sellerString = this.args[1];
      
      //Converting String to Int
      int credits = Integer.parseInt(this.args[2]);

      //Getting the User of the strings
      User buyerUser = this.userStorage.GetByName(buyerString);
      User sellerUser = this.userStorage.GetByName(sellerString);

      //Getting Seller and Buyer Credits
      int buyerCredit = buyerUser.GetCredit();
      int sellerCredit = sellerUser.GetCredit();

      //Adding Credits to buyer and Selling Credits from seller
      buyerCredit += credits;
      sellerCredit -= credits;

      //Setting buyer and seller credits 
      buyerUser.SetCredit(buyerCredit);
      sellerUser.SetCredit(sellerCredit); 

      //Update buyer and seller
      this.userStorage.Update(buyerUser);
      this.userStorage.Update(sellerUser);
    }

}
