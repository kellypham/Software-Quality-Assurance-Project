package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.UserStorage;

public class CreateCommand extends Command {
    private String[] args;
    private UserStorage userStorage;

	public CreateCommand(String[] args)
	{
		super(args);
        this.args = args;
    }


	public void Validate() throws Exception{
        //The amount of credits is greater than 999,999 
        String balance = this.args[3];
        if (Integer.parseInt(balance) > 999999){
            throw new Exception("The balance is greater than 9999999 ");
        }

        //If there are too many arguments for Create Command
        if (this.args.length != 4){
            throw new Exception("Error: The arguments doesnt have the required length");
        }
    }

    public void Execute(){
        //Creating a new user
        User user = new User();
        
        //Args Variable
        String name = this.args[1];
        String stringType = this.args[2];
        String balance = this.args[3];

        //Executing Variables
        user.SetName(name);
        
        //Getting the user type
        UserType ret = null;
		for (UserType type: UserType.values()){
			if(type.ToString() == stringType){
				ret = type;
			}
		}
        
		//Setting the UserType
        user.SetType(ret);
        user.SetCredit(Integer.parseInt(balance));
        this.userStorage.Create(user);
    }

}
