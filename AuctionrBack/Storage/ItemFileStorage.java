package AuctionrBack.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import AuctionrBack.Models.Item;
import AuctionrBack.Storage.Exceptions.ItemNotFoundException;
import AuctionrBack.Storage.Formatting.StorageFormatter;

/**
 * Implementation of ItemStorage that reads the items from a file
 */
public class ItemFileStorage extends ItemStorage
{
	ArrayList<Item> items;
	private String filename;

	/**
	 * Initializes the ItemFileStorage to load from a specified file
	 * @param filename File to read items from
	 */
	public ItemFileStorage(String filename)
	{
		items = new ArrayList<>();
		this.filename = filename;
	}

	/**
	 * Loads all Items from the specified file into memory to be queried later
	 * @throws FileNotFoundException If the specified file doesn't exist
	 * @throws IOException If there's an issue reading the file
	 */
	public void Open() throws FileNotFoundException, IOException
	{
		FileReader file = new FileReader(filename);
		BufferedReader reader = new BufferedReader(file);

		while(reader.ready())
		{
			String entry = reader.readLine();
			
			if (entry.isEmpty()) continue;

			Item item = Parse(entry);
			items.add(item);
		}

		reader.close();
	}

	/**
	 * Writes all Items to a specified output file
	 * @param outputFile File to output items to
	 * @throws IOException If there's an issue writing to the file
	 */
	public void Write(String outputFile) throws IOException
	{
		FileWriter file = new FileWriter(outputFile);
		BufferedWriter writer = new BufferedWriter(file);

		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);
			String itemString = Parse(item);

			writer.write(itemString);
			writer.newLine();
		}

		writer.close();
	}

	/**
	 * Get all items currently up for auction
	 * @return All items currently up for auction
	 */
	@Override
	public Item[] All()
	{
		Item[] allItems = new Item[items.size()];

		for (int i = 0; i < items.size(); i++)
		{
			Item original = items.get(i);
			Item copy = new Item();

			Assign(original, copy);

			allItems[i] = copy;
		}

		return allItems;
	}

	/**
	 * Get a specific item by its name
	 * @return The Item with a matching name
	 */
	@Override
	public Item GetByName(String name) throws ItemNotFoundException
	{
		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);

			if (item.GetName().equals(name))
			{
				Item result = new Item();
				Assign(item, result);
				return result;
			}
		}

		throw new ItemNotFoundException("Item '" + name + "' not found");
	}

	/**
	 * Create an item. Changes are written to memory, and must be output with Write()
	 * @param Item The Item to create
	 */
	@Override
	public void Create(Item item)
	{
		items.add(item);
	}

	/**
	 * Update an item. Changes are written to memory, and must be output with Write()
	 * @param Item The Item to update
	 * @throws ItemNotFoundException If there is no item matching the name provided
	 */
	@Override
	public void Update(Item toUpdate) throws ItemNotFoundException
	{
		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);

			if (item.GetName().equals(toUpdate.GetName()))
			{
				Assign(toUpdate, item);
				return;
			}
		}

		throw new ItemNotFoundException("Item '" + toUpdate.GetName() + "' not found");
	}

	/**
	 * Delete an item. Changes are written to memory, and must be output with Write()
	 * @param Item The Item to delete
	 */
	public void Delete(Item toDelete)
	{
		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);

			if (item.GetName().equals(toDelete.GetName()))
			{
				items.remove(i);
				return;
			}
		}
	}

	/**
	 * Assigns all properties from one Item to another
	 * @param source Item to get properties from
	 * @param dest Item to write properties to
	 */
	private void Assign(Item source, Item dest)
	{
		dest.SetName(source.GetName());
		dest.SetSellerName(source.GetSellerName());
		dest.SetHighestBidderName(source.GetHighestBidderName());
		dest.SetHighestBid(source.GetHigestBid());
		dest.SetDaysRemaining(source.GetDaysRemaining());
	}

	/**
	 * Parses a line from the Log file into an Item
	 * @param line Line from the Items file to parse
	 * @return Item with properties set from the log file
	 */
	private Item Parse(String line)
	{
		Item i = new Item();
		
		String name = line.substring(0, 20).trim();
		String sellerName = line.substring(20, 36).trim();
		String highestBidderName = line.substring(36, 52).trim();
		int daysRemaining = Integer.parseInt(line.substring(52, 56).trim());
		int highestBid = Integer.parseInt(line.substring(56).trim());

		i.SetName(name);
		i.SetSellerName(sellerName);
		i.SetHighestBidderName(highestBidderName);
		i.SetDaysRemaining(daysRemaining);
		i.SetHighestBid(highestBid);

		return i;
	}

	/**
	 * Parses an item into a string to write to the Items file
	 * @param item Item to parse
	 * @return The line in the Items file representing this object
	 */
	private String Parse(Item item)
	{
		StorageFormatter formatter = new StorageFormatter();

		return
			formatter.Pad(item.GetName(), 19) + ' '
			+ formatter.Pad(item.GetSellerName(), 15) + ' '
			+ formatter.Pad(item.GetHighestBidderName(), 15) + ' '
			+ formatter.Pad(item.GetDaysRemaining(), 3) + ' '
			+ formatter.Pad(item.GetHigestBid(), 6);
	} 
}