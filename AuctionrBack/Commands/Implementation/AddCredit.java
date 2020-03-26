package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.UserStorage;

public class AddCredit extends Command
{
    private UserStorage userStorage;
    
	private final int MAX_CREDIT = 1000;
	
	User user;

	String username;
	int addCredit;

	int newCredit;

	public AddCredit(String[] args, UserStorage userStorage)
	{
		super(args);
		this.userStorage = userStorage;
		/*
		for(int i = 0; i < args.length; i++){
			System.out.println(args[i]);
		}
		*/
		addCredit = Integer.parseInt(args[2]);
    	username = args[0];
    }

	/**
	 * Checks that the user exists and that the amount of
	 *  credit to add isn't above the maximum
	 */
	public void Validate() throws Exception
	{
		user = this.userStorage.GetByName(username);
    	
    	//A maximum of $1000.00 can be added to an account 
		if (addCredit > MAX_CREDIT)
		{
    		throw new Exception("Error: The maximum credit is $1000.00");
    	}
    }

	/**
	 * Adds the credit to the user and updates it in long term storage
	 */
	public void Execute() throws Exception
	{
		user.SetCredit(user.GetCredit() + newCredit);
		userStorage.Update(user);
    }

}