package AuctionrBack;

import AuctionrBack.Commands.Command;
import AuctionrBack.Commands.CommandFactory;
import AuctionrBack.Input.*;
import AuctionrBack.Models.Item;
import AuctionrBack.Models.User;
import AuctionrBack.Storage.*;
import AuctionrBack.Storage.Exceptions.UserNotFoundException;

public class Main {
	private static UserFileStorage userStorage;
	private static ItemFileStorage itemStorage;

	public static void main(String[] args) {
		DailyLogFile log = new DailyLogFile("log.txt");

		userStorage = new UserFileStorage("users.txt");
		itemStorage = new ItemFileStorage("items.txt");

		CommandFactory commandFactory = new CommandFactory(userStorage, itemStorage);

		try {
			log.Initialize();
		} catch (Exception ex) {
			System.out.println("ERROR: Could not initialize log: " + ex);
			System.exit(1);
		}

		try {
			userStorage.Open();
		} catch (Exception ex) {
			System.out.println("ERROR: Could not initialize users: " + ex);
			System.exit(1);
		}

		try {
			itemStorage.Open();
		} catch (Exception ex) {
			System.out.println("ERROR: Could not initialize items: " + ex);
			System.exit(1);
		}

		int entryNumber = 0;
		while (!log.IsEmpty()) {
			entryNumber++;

			LogEntry entry = log.NextItem();
			Command command = commandFactory.Create(entry);
			
			try {
				command.Validate();
			} catch (Exception ex) {
				System.out.println(
						"ERROR: Validation error on line " + entryNumber + ": " + ex.getMessage() + "; Ignoring entry");
				continue;
			}

			try {
				command.Execute();
			} catch (Exception ex) {
				System.out.println("ERROR: Fatal error on line " + entryNumber + ": " + ex.getMessage());

				System.exit(1);
			}
		}

		Item[] items = itemStorage.All();
		for (int i = 0; i < items.length; i++) {
			Item item = items[i];
			try
			{
				DecrementDaysRemaining(item);
			}
			catch (Exception ex)
			{
				System.out.println(ex);
				System.exit(1);
			}
		}

		try {
			userStorage.Write("newusers.txt");
		} catch (Exception ex) {
			System.out.println("ERROR: Could not write new users file: " + ex.getMessage());
		}

		try {
			itemStorage.Write("newitems.txt");
		} catch (Exception ex) {
			System.out.println("ERROR: Could not write new items file: " + ex.getMessage());
		}

		System.out.println("Done");
	}

	private static void DecrementDaysRemaining(Item item) throws Exception
	{
		item.SetDaysRemaining(item.GetDaysRemaining()-1);

		if (item.IsOver())
		{
			User buyer = userStorage.GetByName(item.GetHighestBidderName());
			User seller = userStorage.GetByName(item.GetSellerName());

			int bidAmount = item.GetHigestBid();

			if (buyer.GetCredit() >= bidAmount)
			{
				buyer.SetCredit(buyer.GetCredit()-bidAmount);
				seller.SetCredit(seller.GetCredit()+bidAmount);

				userStorage.Update(buyer);
				userStorage.Update(seller);
				itemStorage.Delete(item);
			}
			else
			{
				throw new Exception(
					"ERROR: highest bidder for item '"
					+ item.GetName()
					+ "': '"
					+buyer.GetName()
					+"' has insufficient balance"
				);
			}
		}
		else
		{
			itemStorage.Update(item);
		}
	}
}