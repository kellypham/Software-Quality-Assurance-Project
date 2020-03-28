package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.ItemStorage;
import AuctionrBack.Storage.UserStorage;

//Class for Delete Command
public class DeleteCommand extends Command
{
	private UserStorage userStorage;
	private ItemStorage itemStorage;

	String username;
	User user;

	public DeleteCommand(String[] args, UserStorage userStorage, ItemStorage itemStorage)
	{
		super(args);
		
		username = args[1];

		this.userStorage = userStorage;
		this.itemStorage = itemStorage;
    }
	
	/**
	 * Checks that the user to delete exists
	 */
	public void Validate() throws Exception
	{
		user = userStorage.GetByName(username);
	}
	
	public void Execute() throws Exception
	{
		userStorage.Delete(user);
		
    }

}
