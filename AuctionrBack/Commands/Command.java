package AuctionrBack.Commands;

public abstract class Command
{
	public Command(String[] args)
	{
	}

	public abstract void Validate();
	public abstract void Execute();
}