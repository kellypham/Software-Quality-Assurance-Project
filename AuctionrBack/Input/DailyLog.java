package AuctionrBack.Input;

/**
 * Abstract class for reading entries from the Daily Log in whichever
 * format they're stored, and parsing t hem into LogEntry objects
 */
public abstract class DailyLog
{
	/**
	 * Do any initialization work that the DailyLog requires here
	 * @throws Exception
	 */
	public abstract void Initialize() throws Exception;

	/**
	 * Return the next item in the Daily Log
	 * @return Next LogEntry in the list
	 */
	public abstract LogEntry NextItem();

	/**
	 * Return true if there's no more Log entries to return in NextItem() false otherwise
	 * @return Whether there's still log entries to parse
	 */
	public abstract boolean IsEmpty();
}