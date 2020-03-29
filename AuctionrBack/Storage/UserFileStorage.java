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

/**
 * Implementation of UserStorage that reads Users from a file
 */
public class UserFileStorage extends UserStorage
{
	/**
	 * All users
	 */
	ArrayList<User> users;
	/**
	 * File name to read users from
	 */
	private String filename;

	/**
	 * Initializes the file to read users from
	 * @param filename Users file to read
	 */
	public UserFileStorage(String filename)
	{
		users = new ArrayList<>();
		this.filename = filename;
	}

	/**
	 * Reads Users from the users file, stores them in memory
	 * @throws FileNotFoundException If the Users file doesn't exist
	 * @throws IOException If there is an issue reading the Users file
	 */
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

	/**
	 * Write all changes to a specified output file
	 * @param outputFile File to write to
	 * @throws IOException when there is an issue writing to the specified file
	 */
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

	/**
	 * Get a user by their name
	 * @param name Name of the user to get
	 * @throws UserNotFoundException When no user exists with the given name
	 * @return User matching the given name
	 */
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

	/**
	 * Create a user. User will be written to the file created with Write()
	 * @param user User to create
	 */
	@Override
	public void Create(User user)
	{
		users.add(user);
	}

	/**
	 * Update a user. Changes will be written to the file created with Write()
	 * @param user User to update
	 */
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
	
	/**
	 * Delete a user. Changes will be written to the file created with Write()
	 * @param user User to delete
	 */
	@Override
	public void Delete(User toDelete)
	{
		for (int i = 0; i < users.size(); i++)
		{
			User user = users.get(i);

			if (user.GetName().equals(toDelete.GetName()))
			{
				users.remove(i);
				return;
			}
		}
	}
	/**
	 * Return ArrayList. Allows other commands to check properties of the list of users
	 * @param returns ArrayList
	 */
	public ArrayList<User> ReturnArrayUser(){
		return users;
	}

	/**
	 * Assign all properties from one user to another
	 * @param source User to read properties from
	 * @param dest User to write properties to
	 */
	private void Assign(User source, User dest)
	{
		dest.SetName(source.GetName());
		dest.SetType(source.GetType());
		dest.SetCredit(source.GetCredit());
	}

	/**
	 * Parses a line from the Users file into a User object
	 * @param line line from the Users file to parse
	 * @return User with its values initialized from the given line
	 */
	private User Parse(String line)
	{
		User user = new User();

		String name = line.substring(0, 16).trim();
		String type = line.substring(16, 19).trim();
		int credit = Integer.parseInt(line.substring(19).trim());

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

	/**
	 * Parses a user into a string to write to a Users file
	 * @param user User to parse
	 * @return Line to write to the Users file
	 */
	private String Parse(User user)
	{
		StorageFormatter formatter = new StorageFormatter();

		return formatter.Pad(user.GetName(), 15) + ' '
			+ user.GetType().ToString() + ' '
			+ formatter.Pad(user.GetCredit(), 9);
	}
}