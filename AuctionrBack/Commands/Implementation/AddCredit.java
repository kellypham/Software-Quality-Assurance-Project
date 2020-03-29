package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.UserStorage;

public class AddCredit extends Command
{
    private String[] args;
	private UserStorage userStorage;
	private final int MAX_CREDIT = 1000;

	public AddCredit(String[] args, UserStorage userStorage)
	{
		super(args);
		this.userStorage = userStorage;
		this.args = args;
		
    }

	/**
	 * Checks that the user exists and that the amount of
	 *  credit to add isn't above the maximum
	 */
	public void Validate() throws Exception
	{
		String credit = this.args[1];
		
		//user name must be existing with the exception
		String userName = this.args[0];
		User user = this.userStorage.GetByName(userName);
    	
    	//A maximum of $1000.00 can be added to an account 
		if (Integer.parseInt(credit) > MAX_CREDIT)
		{
    		throw new Exception("Error: The maximum credit is $1000.00");
    	}
		
		//The minimum credit must be greater than 0
		if (Integer.parseInt(credit) <= 0)
		{
		    throw new Exception("Error: The minimum credit is greater than $0.00");
		 }
    }

	/**
	 * Adds the credit to the user and updates it in long term storage
	 */
	public void Execute() throws Exception
	{
		String userName = this.args[0];
		User user = this.userStorage.GetByName(userName);
		
        String credit = this.args[1];
        
		user.SetCredit(user.GetCredit() + Integer.parseInt(credit));
		userStorage.Update(user);
    }

}