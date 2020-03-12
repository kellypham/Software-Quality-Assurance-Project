package AuctionrBack.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import AuctionrBack.Models.User;
import AuctionrBack.Models.UserType;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;
import AuctionrBack.Storage.Formatting.StorageFormatter;

public class UserFileStorage extends UserStorage
{
	ArrayList<User> users;
	private String filename;

	public UserFileStorage(String filename)
	{
		users = new ArrayList<>();
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

			User user = Parse(entry);
			users.add(user);
		}

		reader.close();
	}

	public void Write(String outputFile) throws IOException
	{
		FileWriter file = new FileWriter(outputFile);
		BufferedWriter writer = new BufferedWriter(file);

		for (int i = 0; i < users.size(); i++)
		{
			User item = users.get(i);
			String itemString = Parse(item);

			writer.write(itemString);
			writer.newLine();
		}

		writer.close();
	}

	@Override
	public User GetByName(String name) throws UserNotFoundException
	{
		for (int i = 0; i < users.size(); i++)
		{
			User user = users.get(i);

			if (user.GetName().equals(name))
			{
				User result = new User();
				Assign(user, result);
				return result;
			}
		}

		throw new UserNotFoundException("User '" + name + "' not found");
	}

	@Override
	public void Create(User item)
	{
		users.add(item);
	}

	@Override
	public void Update(User toUpdate) throws UserNotFoundException
	{
		for (int i = 0; i < users.size(); i++)
		{
			User user = users.get(i);

			if (user.GetName().equals(toUpdate.GetName()))
			{
				Assign(toUpdate, user);
				return;
			}
		}

		throw new UserNotFoundException("User '" + toUpdate.GetName() + "' not found");
	}

	private void Assign(User source, User dest)
	{
		dest.SetName(source.GetName());
		dest.SetType(source.GetType());
		dest.SetCredit(source.GetCredit());
	}

	private User Parse(String line)
	{
		User user = new User();
		
		String name = line.substring(0, 15);
		String type = line.substring(16, 18);
		int credit = Integer.parseInt(line.substring(19));

		user.SetName(name);
		user.SetCredit(credit);

		for (UserType t : UserType.values())
		{
			if (t.ToString().equals(type))
			{
				user.SetType(t);
				break;
			}
		}

		return user;
	}

	private String Parse(User user)
	{
		StorageFormatter formatter = new StorageFormatter();

		return formatter.Pad(user.GetName(), 15) + ' '
			+ user.GetType().ToString() + ' '
			+ formatter.Pad(user.GetCredit(), 9);
	}
}