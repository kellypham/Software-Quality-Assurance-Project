package AuctionrBack.Storage;

import java.io.IOException;

import AuctionrBack.Models.Item;
import AuctionrBack.Storage.Exceptions.ItemNotFoundException;

public abstract class ItemStorage
{
	public abstract Item GetByName(String name) throws ItemNotFoundException;
	public abstract void Create(Item item);
	public abstract void Update(Item item) throws ItemNotFoundException;
}