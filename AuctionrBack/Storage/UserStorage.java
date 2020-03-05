package AuctionrBack.Storage;

import AuctionrBack.Models.User;

public abstract class UserStorage
{
	public abstract User GetByName(String name);
	public abstract void Create(User user);
	public abstract void Update(User user);
}