package AuctionrBack.Models;

/** Enum for user types */
public enum UserType
{
    ADMIN("AA"),
    FULL_STANDARD("FS"),
    BUY_STANDARD("BS"),
	SELL_STANDARD("SS");
	
	private String toStr;
	UserType(String enumStr)
	{
		this.toStr = enumStr;
	}

	public String ToString()
	{
		return this.toStr;
	}
}