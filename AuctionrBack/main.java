package AuctionrBack;

import AuctionrBack.Commands.Command;
import AuctionrBack.Commands.CommandFactory;
import AuctionrBack.Input.*;
import AuctionrBack.Storage.*;

class Main
{
	public static void main(String[] args)
	{
		DailyLog log;

		UserStorage userStorage;
		ItemStorage itemStorage;

		CommandFactory commandFactory = new CommandFactory(userStorage, itemStorage);

		log.Initialize();

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

		System.out.println("Done");
	}
}