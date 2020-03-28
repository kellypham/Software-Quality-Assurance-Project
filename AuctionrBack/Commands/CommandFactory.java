package AuctionrBack.Commands;

import AuctionrBack.Input.LogEntry;
import AuctionrBack.Storage.*;
import AuctionrBack.Commands.Implementation.*;

public class CommandFactory
{
	private static final String CODE_CREATE = "01";
	private static final String CODE_ADD_CREDIT = "06";
	private static final String CODE_ADVERTISE = "03";
	private static final String CODE_BID = "04";
	private static final String CODE_DELETE = "02";
	private static final String CODE_REFUND = "05";


	private UserStorage userStorage;
	private ItemStorage itemStorage;

	public CommandFactory(UserStorage userStorage, ItemStorage itemStorage)
	{
		this.userStorage = userStorage;
		this.itemStorage = itemStorage;
	}

	public Command Create(LogEntry entry)
	{
		String code = entry.TransactionCode();
		String[] args = entry.Arguments();

		if (code.equals(CODE_CREATE))
		{
			return new CreateCommand(args, userStorage);
		}
		else if (code.equals(CODE_DELETE))
		{
			return new DeleteCommand(args, userStorage, itemStorage);
		}
		else if (code.equals(CODE_ADD_CREDIT))
		{
			return new AddCredit(args, userStorage);
		}
		else if (code.equals(CODE_ADVERTISE))
		{
			return new Advertise(args);
		}
		else if (code.equals(CODE_BID))
		{
			return new Bid(args);
		}
		else if (code.equals(CODE_REFUND))
		{
			return new Refund(args);
		}

		return null;	
	}
}