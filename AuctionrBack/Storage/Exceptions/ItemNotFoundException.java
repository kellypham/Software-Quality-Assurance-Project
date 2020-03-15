package AuctionrBack.Storage.Exceptions;

/**
 * Exception for errors in querying or modifying Items
 */
public class ItemNotFoundException extends Exception
{
	/**
	 * Initializes the error message of the exception
	 * @param message 
	 */
	public ItemNotFoundException(String message)
	{
		super(message);
	}
}