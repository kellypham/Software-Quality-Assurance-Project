package AuctionrBack.Input;

public abstract class DailyLog
{
	public abstract void Initialize() throws Exception;
	public abstract LogEntry NextItem();
	public abstract boolean IsEmpty();
}