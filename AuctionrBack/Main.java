package AuctionrBack;

import AuctionrBack.Commands.Command;
import AuctionrBack.Commands.CommandFactory;
import AuctionrBack.Input.*;
import AuctionrBack.Storage.*;

public class Main
{
	public static void main(String[] args)
	{
		DailyLogFile log = new DailyLogFile("log.txt");

		UserFileStorage userStorage = new UserFileStorage("users.txt");
		ItemFileStorage itemStorage = new ItemFileStorage("items.txt");

		CommandFactory commandFactory = new CommandFactory(userStorage, itemStorage);

		try
		{
			log.Initialize();
		}
		catch (Exception ex)
		{
			System.out.println("Could not initialize log: " + ex.getMessage());
			System.exit(1);
		}

		int entryNumber = 0;
		while (!log.IsEmpty())
		{
			entryNumber++;

			LogEntry entry = log.NextItem();
			Command command = commandFactory.Create(entry);

			try
			{
				command.Validate();
			}
			catch (Exception ex)
			{
				System.out.println(
					"Validation error on line "
					+ entryNumber
					+ ": "
					+ ex.getMessage()
					+ "; Ignoring entry"
				);
				continue;
			}

			try
			{
				command.Execute();
			}
			catch (Exception ex)
			{
				System.out.println(
					"Fatal error on line "
					+ entryNumber
					+ ": "
					+ ex.getMessage()
				);

				System.exit(1);
			}
		}

		try
		{
			userStorage.Write("newusers.txt");
		}
		catch (Exception ex)
		{
			System.out.println("Could not write new users file: " + ex.getMessage());
		}

		try
		{
			itemStorage.Write("newitems.txt");
		}
		catch (Exception ex)
		{
			System.out.println("Could not write new items file: " + ex.getMessage());
		}

		System.out.println("Done");
	}
}