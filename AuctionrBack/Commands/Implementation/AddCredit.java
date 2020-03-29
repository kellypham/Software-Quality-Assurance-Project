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

	public AddCredit(String[] args, UserStorage userStorage)
	{
		super(args);
		this.userStorage = userStorage;
		
		username = args[0];
		addCredit = Integer.parseInt(args[2]);
    }

	/**
	 * Checks that the user exists and that the amount of
	 *  credit to add isn't above the maximum
	 */
	public void Validate() throws Exception
	{
		user = userStorage.GetByName(username);
    	
		if (addCredit > MAX_CREDIT)
		{
    		throw new IllegalArgumentException("Error: The maximum credit is $" + MAX_CREDIT);
		}
		else if (addCredit < 0)
		{
			throw new IllegalArgumentException("Error: The amount of credit cannot be below 0");
		}
    }

	/**
	 * Adds the credit to the user and updates it in long term storage
	 */
	public void Execute() throws Exception
	{
		user.SetCredit(user.GetCredit() + addCredit);
		userStorage.Update(user);
    }

}