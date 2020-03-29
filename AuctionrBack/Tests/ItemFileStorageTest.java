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
	final String EXISTING_ITEM = "test_item";
	final String EXISTING_SELLER = "sellerone";

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
		final String SELLER_NAME = "seller";
		final String OUT_FILE = "testNewItems.txt";

		//Initialize a new Item file
		Item item = new Item();
		item.SetName(ITEM_NAME);
		item.SetSellerName(SELLER_NAME);
		
		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Create(item);
		storage.Write(OUT_FILE);

		//Read the new Item file
		storage = new ItemFileStorage(OUT_FILE);
		storage.Open();

		//Check that the new file has the contents of the old
		Item inStorage = storage.Query(ITEM_NAME, SELLER_NAME);
		assertEquals(item, inStorage);

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
	public void Query() throws Exception
	{
		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Open();

		Item item = storage.Query(EXISTING_ITEM, EXISTING_SELLER);
		assertEquals(EXISTING_ITEM, item.GetName());
		assertEquals(EXISTING_SELLER, item.GetSellerName());
	}

	@Test(expected=ItemNotFoundException.class)
	public void ItemNameNotFound() throws Exception
	{
		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Query("ThisItemDoesNotExist", EXISTING_SELLER);
	}

	@Test(expected=ItemNotFoundException.class)
	public void SellerNameNotFound() throws Exception
	{
		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Query(EXISTING_ITEM, "sellerNotFound");
	}

	@Test
	public void CreateItem() throws Exception
	{
		final String ITEM_NAME = "Item";

		Item item = new Item();
		item.SetName(ITEM_NAME);
		item.SetSellerName(EXISTING_SELLER);

		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Create(item);

		Item inStorage = storage.Query(ITEM_NAME, EXISTING_SELLER);
		assertEquals(item, inStorage);
	}

	@Test
	public void UpdateItem() throws Exception
	{
		final String ITEM_NAME = "Item";
		
		Item item = new Item();
		item.SetName(ITEM_NAME);
		item.SetSellerName(EXISTING_SELLER);

		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Create(item);

		item.SetDaysRemaining(10);
		storage.Update(item);

		Item inStorage = storage.Query(ITEM_NAME, EXISTING_SELLER);
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
	public void DeleteItem() throws Exception
	{
		final String ITEM_NAME = "Item";
		
		Item item = new Item();
		item.SetName(ITEM_NAME);
		item.SetSellerName(EXISTING_SELLER);

		ItemFileStorage storage = new ItemFileStorage(Item_FILE);
		storage.Create(item);
		storage.Delete(item);
		storage.Query(ITEM_NAME, EXISTING_SELLER);
	}
}