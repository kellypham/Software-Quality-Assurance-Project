package AuctionrBack.Storage;

import AuctionrBack.Models.User;
import java.util.ArrayList;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;

public abstract class UserStorage
{
	/**
	 * Returns the user with a name matching the one provided
	 * @throws UserNotFoundException If no user in storage has that name
	 * @return The user matching the name
	 */
	public abstract User GetByName(String name) throws UserNotFoundException;

	/**
	 * Add a user to long term storage
	 * @param user the User to create
	 */
	public abstract void Create(User user);

	/**
	 * Update a user in long term storage
	 * @param user The User to update
	 * @throws UserNotFoundException If the user doesn't exist in long term storage
	 */
	public abstract void Update(User user) throws UserNotFoundException;

	/**
	 * Remove a user from long term storage
	 * @param user The user to delete 
	 */
	public abstract void Delete(User user);
	
	
	public abstract ArrayList<User> ReturnArrayUser();
}