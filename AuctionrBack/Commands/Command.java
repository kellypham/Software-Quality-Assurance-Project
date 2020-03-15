package AuctionrBack.Commands;

import AuctionrBack.Storage.Exceptions.*;

public abstract class Command
{
	public Command(String[] args)
	{
	}

	public abstract void Validate() throws MyException, ItemNotFoundException, UserNotFoundException;
	public abstract void Execute() throws ItemNotFoundException, UserNotFoundException;
}
