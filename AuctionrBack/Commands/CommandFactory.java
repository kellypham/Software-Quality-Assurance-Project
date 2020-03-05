package AuctionrBack.Commands;

import AuctionrBack.Input.LogEntry;
import AuctionrBack.Storage.*;

public class CommandFactory
{
	private UserStorage userStorage;
	private ItemStorage itemStorage;

	public CommandFactory(UserStorage userStorage, ItemStorage itemStorage)
	{
		this.userStorage = userStorage;
		this.itemStorage = itemStorage;
	}

	public Command Create(LogEntry entry)
	{
		return null;
	}
}