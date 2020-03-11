package AuctionrBack.Models;

/** Enum for user types */
enum UserType{
    ADMIN,
    FULL_STANDARD,
    BUY_STANDARD,
    SELL_STANDARD
}

/**
 * Class storing user data
 */
public class User
{
	private String name;
    private UserType type;
    private int credit;
    /**
    * Accessor for user's name.
    * @return User's name
    */
	public String GetName()
	{
        return this.name;
    }
    /**
    * Mutator for user's name
    * @param name New name value
    */
	public void SetName(String name)
	{
        this.name = name;
    }

    /**
    * Accessor for user's type.
    * @return User's type from UserTypes enum
    */
	public UserType GetType()
	{
        return this.type;
    }
    /**
    * Mutator for user's type
    * @param name New user type
    */
	public void SetType(UserType type)
	{
        this.type = type;
    }

    /**
    * Accessor for user's credit.
    * @return User's credit
    */
	public int GetCredit()
	{
        return this.credit;
    }

    /**
    * Mutator for user's credit
    * @param name New credit value
    */
	public void SetCredit(int credit)
	{
        this.credit = credit;
    }

    /**
    * Accessor for user's admin type.
    * @return True if User is an Admin
    */
	public boolean IsAdmin()
	{
		return this.type == UserType.ADMIN;
    }
    
}