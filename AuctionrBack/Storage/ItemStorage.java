package AuctionrBack.Storage;

import AuctionrBack.Models.Item;
import AuctionrBack.Storage.Exceptions.DuplicateItemException;
import AuctionrBack.Storage.Exceptions.ItemNotFoundException;

/**
 * Class for handling long term storage of Items
 */
public abstract class ItemStorage
{
	/**
	 * Returns all items in storage
	 * @return All items in storage
	 */
	public abstract Item[] All();
	/**
	 * Gets an Item by its name
	 * @param name Item name
	 * @param sellerName Seller's name
	 * @throws ItemNotFoundException when no matching item is found
	 * @return Item matching the name
	 */
	public abstract Item Query(String name, String sellerName) throws ItemNotFoundException;

	/**
	 * Add an item to long term storage
	 * @param item Item to create
	 */
	public abstract void Create(Item item) throws DuplicateItemException;
	
	/**
	 * Update an item in long term storage
	 * @param item Item to update
	 * @throws ItemNotFoundException If the item doesn't already exist in long term storage
	 */
	public abstract void Update(Item item) throws ItemNotFoundException;

	/**
	 * Removes an item from long term storage
	 * @param item The item to remove
	 */
	public abstract void Delete(Item item);
}