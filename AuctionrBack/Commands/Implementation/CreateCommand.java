package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.UserStorage;

public class CreateCommand extends Command {
    private String[] args;
    private UserStorage userStorage;
    
    //Variables for Validate and Execute
    int balance;
    String username;
    String stringType;
    User user;

	public CreateCommand(String[] args, UserStorage userStorage)
	{
		super(args);
		this.userStorage = userStorage;
		
		//Setting variables equal to the args
		balance = Integer.parseInt(args[2]);
		stringType = args[1];
		username = args[0];
    }


	public void Validate() throws Exception{
        
        //If the user is already in the database
        if (userStorage.GetByName(username) != null){
        	throw new Exception("User already exists");
        }
        //If the balance is greater than 9999999
        if (balance > 999999){
            throw new Exception("The balance is greater than 9999999 ");
        }

        //If there are too many arguments for Create Command
        if (this.args.length != 3){
            throw new Exception("Error: The arguments doesnt have the required length");
        }
    }

    public void Execute(){
      
    	//Setting UserName
        user.SetName(username);
        
        //Getting the user type
        UserType ret = null;
		for (UserType type: UserType.values()){
			if(type.ToString() == stringType){
				ret = type;
			}
		}
    
		//Setting the User info and creating the user
        user.SetType(ret);
        user.SetCredit(balance);
        this.userStorage.Create(user);
    }

}
