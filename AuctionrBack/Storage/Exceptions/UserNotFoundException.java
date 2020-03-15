package AuctionrBack.Storage.Exceptions;

/**
 * Exception for errors querying or modifying User objects in storage
 */
public class UserNotFoundException extends Exception
{
	/**
	 * Initializes the error message of the exception
	 * @param message 
	 */
	public UserNotFoundException(String message)
	{
		super(message);
	}
}