package AuctionrBack.Input;

public abstract class DailyLog
{
	public abstract void Initialize();
	public abstract LogEntry NextItem();
	public abstract boolean IsEmpty();
}