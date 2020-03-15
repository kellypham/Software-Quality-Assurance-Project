package AuctionrBack.Input;

/**
 * Class for storing the details of an entry in a daily log
 */
public class LogEntry
{
	/** Transaction code of the log entry */
	private String transactionCode;
	/** Log entry arguments */
	private String[] arguments;

	/**
	 * Constructor for LogEntry, sets properties of the object
	 * @param code Transaction code for the entry
	 * @param args Arguments for the entry
	 */
	public LogEntry(String code, String[] args)
	{
		transactionCode = code;
		arguments = args;
	}

	/**
	 * Get the transaction code of the log entry
	 * @return Transaction Code for the log entry
	 */
	public String TransactionCode()
	{
		return transactionCode;
	}

	/**
	 * Get the arguments list of the log entry as strings
	 * @return arguments list for the log entry
	 */
	public String[] Arguments()
	{
		return arguments;
	}
}