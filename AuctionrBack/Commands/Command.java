package AuctionrBack.Commands;

public abstract class Command
{
	public Command(String code, String[] args)
	{
	}

	public abstract void Validate();
	public abstract void Execute();
}