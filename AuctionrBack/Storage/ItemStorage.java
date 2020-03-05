package AuctionrBack.Storage;

import AuctionrBack.Models.Item;

public abstract class ItemStorage
{
	public abstract Item GetByName(String name);
	public abstract void Create(Item item);
	public abstract void Update(Item item);
}