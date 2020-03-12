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

public class ItemFileStorage extends ItemStorage
{
	ArrayList<Item> items;
	private String filename;

	public ItemFileStorage(String filename)
	{
		items = new ArrayList<>();
		this.filename = filename;
	}

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

	@Override
	public void Create(Item item)
	{
		items.add(item);
	}

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

	private void Assign(Item source, Item dest)
	{
		dest.SetName(source.GetName());
		dest.SetSellerName(source.GetSellerName());
		dest.SetHighestBidderName(source.GetHighestBidderName());
		dest.SetHighestBid(source.GetHigestBid());
		dest.SetDaysRemaining(source.GetDaysRemaining());
	}

	private Item Parse(String line)
	{
		Item i = new Item();
		return i;
	}

	private String Parse(Item item)
	{
		return "";
	} 
}