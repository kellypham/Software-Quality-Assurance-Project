package AuctionrBack.Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import AuctionrBack.Models.User;
import AuctionrBack.Models.UserType;
import AuctionrBack.Storage.UserFileStorage;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;

public class UserFileStorageTest
{
	final String USER_FILE = "users.txt";

	@Test
	public void LoadsUsers() throws Exception
	{
		UserFileStorage storage = new UserFileStorage(USER_FILE);
		storage.Open();
	}

	@Test
	public void WritesUsers() throws Exception
	{
		final String USER_NAME = "TestUser";
		final String OUT_FILE = "testNewUsers.txt";

		//Initialize a new user file
		User user = new User();
		user.SetName(USER_NAME);
		
		UserFileStorage storage = new UserFileStorage(USER_FILE);
		storage.Create(user);
		storage.Write(OUT_FILE);

		//Read the new user file
		storage = new UserFileStorage(OUT_FILE);
		storage.Open();

		//Check that the new file has the contents of the old
		User inStorage = storage.GetByName(USER_NAME);
		assertEquals(user, inStorage);

		//Clean up
		File f = new File(OUT_FILE);
		f.delete();
	}
	
	@Test(expected=FileNotFoundException.class)
	public void UserFileNotFound() throws Exception
	{
		UserFileStorage storage = new UserFileStorage("DoesNotExist.txt");
		storage.Open();
	}

	@Test
	public void QueryUserByName() throws Exception
	{
		final String QUERY_NAME = "admin";

		UserFileStorage storage = new UserFileStorage(USER_FILE);
		storage.Open();

		User user = storage.GetByName(QUERY_NAME);
		assertEquals(QUERY_NAME, user.GetName());
	}

	@Test(expected=UserNotFoundException.class)
	public void UserNameNotFound() throws Exception
	{
		UserFileStorage storage = new UserFileStorage(USER_FILE);
		storage.GetByName("ThisUserDoesNotExist");
	}

	@Test
	public void CreateUser() throws UserNotFoundException
	{
		final String USER_NAME = "user";

		User user = new User();
		user.SetName(USER_NAME);

		UserFileStorage storage = new UserFileStorage(USER_FILE);
		storage.Create(user);

		User inStorage = storage.GetByName(USER_NAME);
		assertEquals(user, inStorage);
	}

	@Test
	public void UpdateUser() throws UserNotFoundException
	{
		final String USER_NAME = "user";
		
		User user = new User();
		user.SetName(USER_NAME);
		user.SetType(UserType.FULL_STANDARD);
		user.SetCredit(1);

		UserFileStorage storage = new UserFileStorage(USER_FILE);
		storage.Create(user);

		user.SetType(UserType.ADMIN);
		storage.Update(user);

		User inStorage = storage.GetByName(USER_NAME);
		assertEquals(user, inStorage);
	}

	@Test(expected=UserNotFoundException.class)
	public void UpdateNonExistingUser() throws UserNotFoundException
	{
		User user = new User();
		user.SetName("aaaaa");

		UserFileStorage storage = new UserFileStorage(USER_FILE);
		storage.Update(user);
	}

	@Test(expected=UserNotFoundException.class)
	public void DeleteUser() throws UserNotFoundException
	{
		final String USER_NAME = "user";
		
		User user = new User();
		user.SetName(USER_NAME);

		UserFileStorage storage = new UserFileStorage(USER_FILE);
		storage.Create(user);
		storage.Delete(user);
		storage.GetByName(USER_NAME);
	}
}