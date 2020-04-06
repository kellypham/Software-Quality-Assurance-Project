package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.UserStorage;
import java.util.ArrayList;

public class CreateCommand extends Command {
    private String[] args;
    private UserStorage userStorage;
    
    //Variables for Validate and Execute
    private int balance;
    private String username;
    private String stringType;
    private User user = new User();
    private ArrayList<User> users;

	public CreateCommand(String[] args, UserStorage userStorage)
	{
		super(args);
		this.args = args;
		this.userStorage = userStorage;
		
		//Setting variables equal to the args
		balance = Integer.parseInt(args[2]);
		stringType = args[1];
		username = args[0];
		
    }


	public void Validate() throws Exception{
        
		
		//If the user chooses a username that is taken already
        users = userStorage.ReturnArrayUser();
        for (User temp : users){
        	if (temp.GetName().equals(username)){
        		throw new Exception("Error: The username has been already declared");
        	}
        }
        
        //If the balance is greater than 9999999
        if (balance > 999999){
            throw new Exception("The balance is greater than 9999999 ");
        }

        //If there are too many arguments for Create Command
        if (this.args.length != 3){
            throw new Exception("Error: The arguments doesnt have the required length");
        }
        
        //If they create an account with 0 or -negative credits
        if (balance < 1){
        	throw new Exception("The balance is smaller than 1");
        }
        
        //If the type (eg. AA, FS, BS, SS) is correct
        boolean rightOrWrong = false;
        for (UserType type: UserType.values()){
			if(type.ToString().equals(stringType)){
				rightOrWrong = true;
			}
		}
        if (rightOrWrong == false){
        	throw new Exception("Error: The UserType is incorrect");
        }
         
        
        
    }

    public void Execute(){
      
    	//Setting UserName
        user.SetName(username);
        
        //Getting the user type
        UserType ret = null;
		for (UserType type: UserType.values()){
			if(type.ToString().equals(stringType)){
				ret = type;
			}
		}
    
		//Setting the User info and creating the user
        user.SetType(ret);
        user.SetCredit(balance);
        
        //Also Creates the user
        this.userStorage.Create(user);
    }

}
