package AuctionrBack.Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import AuctionrBack.Models.Item;
import AuctionrBack.Storage.ItemFileStorage;
import AuctionrBack.Storage.Exceptions.ItemNotFoundException;

public class ItemFileStorageTest
{
	final String Item_FILE = "Items.txt";

	@Test
	public void LoadsItems() throws Exception
	{
		final int TEST_FILE_ITEM_COUNT = 1;

		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Open();

		//Check the correct amount of items have been loaded
		Item[] all = storage.All();
		assertEquals("The number of items loaded differs from the expected amount",
			TEST_FILE_ITEM_COUNT, all.length
		);
	}

	@Test
	public void WritesItems() throws Exception
	{
		final String ITEM_NAME = "TestItem";
		final String OUT_FILE = "testNewItems.txt";

		//Initialize a new Item file
		Item Item = new Item();
		Item.SetName(ITEM_NAME);
		
		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Create(Item);
		storage.Write(OUT_FILE);

		//Read the new Item file
		storage = new ItemFileStorage(OUT_FILE);
		storage.Open();

		//Check that the new file has the contents of the old
		Item inStorage = storage.GetByName(ITEM_NAME);
		assertEquals(Item, inStorage);

		//Clean up
		File f = new File(OUT_FILE);
		f.delete();
	}
	
	@Test(expected=FileNotFoundException.class)
	public void ItemFileNotFound() throws Exception
	{
		ItemFileStorage storage = new ItemFileStorage("DoesNotExist.txt");
		storage.Open();
	}

	@Test
	public void QueryItemByName() throws Exception
	{
		final String QUERY_NAME = "test_item";

		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Open();

		Item Item = storage.GetByName(QUERY_NAME);
		assertEquals(QUERY_NAME, Item.GetName());
	}

	@Test(expected=ItemNotFoundException.class)
	public void ItemNameNotFound() throws Exception
	{
		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.GetByName("ThisItemDoesNotExist");
	}

	@Test
	public void CreateItem() throws ItemNotFoundException
	{
		final String ITEM_NAME = "Item";

		Item Item = new Item();
		Item.SetName(ITEM_NAME);

		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Create(Item);

		Item inStorage = storage.GetByName(ITEM_NAME);
		assertEquals(Item, inStorage);
	}

	@Test
	public void UpdateItem() throws ItemNotFoundException
	{
		final String ITEM_NAME = "Item";
		
		Item item = new Item();
		item.SetName(ITEM_NAME);

		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Create(item);

		item.SetDaysRemaining(10);
		storage.Update(item);

		Item inStorage = storage.GetByName(ITEM_NAME);
		assertEquals(item, inStorage);
	}

	@Test(expected=ItemNotFoundException.class)
	public void UpdateNonExistingItem() throws ItemNotFoundException
	{
		Item Item = new Item();
		Item.SetName("aaaaa");

		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Update(Item);
	}

	@Test(expected=ItemNotFoundException.class)
	public void DeleteItem() throws ItemNotFoundException
	{
		final String ITEM_NAME = "Item";
		
		Item Item = new Item();
		Item.SetName(ITEM_NAME);

		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Create(Item);
		storage.Delete(Item);
		storage.GetByName(ITEM_NAME);
	}
}