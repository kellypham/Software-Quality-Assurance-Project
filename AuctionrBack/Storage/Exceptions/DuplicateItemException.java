package AuctionrBack.Storage.Exceptions;

/**
 * Exception for an error creating an item already for sale
 */
public class DuplicateItemException extends Exception
{
	/**
	 * Initializes the error message of the exception
	 * @param message 
	 */
	public DuplicateItemException(String message)
	{
		super(message);
	}
}