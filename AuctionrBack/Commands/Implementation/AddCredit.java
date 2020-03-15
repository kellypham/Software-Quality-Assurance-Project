package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.UserStorage;
import AuctionrBack.Storage.Exceptions.*;

public class AddCredit extends Command {
	
	private String[] args;
    private UserStorage userStorage;
    
    private final int maxCredit = 1000;

    public AddCredit(String[] args){
		super(args);
    }

    public void Validate() throws MyException, UserNotFoundException {
    	String newcredit = this.args[1];
    	String userName = this.args[2];
    	User user = this.userStorage.GetByName(userName);
    	UserType type = user.GetType();
    	
    	//User must be an admin account
    	if (type.toString() != "AA") {
    		throw new MyException("Error: User must be an admin account");
    	}
    	
    	//A maximum of $1000.00 can be added to an account 
    	if (Integer.parseInt(newcredit) > maxCredit) {
    		throw new MyException("Error: The maximum credit is $1000.00");
    	}
    }

    //add credit into the system for the purchase of accounts
    public void Execute() throws ItemNotFoundException, UserNotFoundException{
    	//Args Variable
        String newcredit = this.args[1];
        String userName = this.args[2];
        
        //Finding the User
        User user = this.userStorage.GetByName(userName);
        
        int credit = user.GetCredit();
        credit += Integer.parseInt(newcredit);

        //Execute the variable
        user.SetCredit(credit);
    }

}
