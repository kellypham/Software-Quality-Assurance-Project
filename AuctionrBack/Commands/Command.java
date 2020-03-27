package AuctionrBack.Commands;

import AuctionrBack.Storage.Exceptions.*;

public abstract class Command
{
	public Command(String[] args)
	{
		
	}

	public abstract void Validate() throws Exception;
	public abstract void Execute() throws Exception;
}
