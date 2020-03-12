package AuctionrBack.Storage;

import AuctionrBack.Models.User;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;

public abstract class UserStorage
{
	public abstract User GetByName(String name) throws UserNotFoundException;
	public abstract void Create(User user);
	public abstract void Update(User user) throws UserNotFoundException;
}