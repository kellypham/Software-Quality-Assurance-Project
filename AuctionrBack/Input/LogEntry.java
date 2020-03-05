package AuctionrBack.Input;

public class LogEntry
{
	private String transactionCode;
	private String[] arguments;

	public LogEntry(String code, String[] args)
	{
		transactionCode = code;
		arguments = args;
	}

	public String TransactionCode()
	{
		return transactionCode;
	}

	public String[] Arguments()
	{
		return arguments;
	}
}