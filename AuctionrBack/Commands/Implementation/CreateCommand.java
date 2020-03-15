package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;

public class CreateCommand extends Command {
    private String[] args;
    private UserStorage userStorage;

	public CreateCommand(String[] args)
	{
		super(args);
        this.args = args;
    }


    public void Validate() throws MyException{
        //The amount of credits is greater than 999,999 
        String balance = this.args[3];
        if (Integer.parseInt(balance) > 999999){
            throw new MyException("The balance is greater than 9999999: " + balance);
        }

        //If there are too many arguments for Create Command
        if (this.args.length != 4){
            throw new MyException("Error: The arguments doesnt have the required length: " + this.args.length);
        }

    }

    public void Execute(){
        //Creating a new user
        User user = new User();
        
        //Args Variable
        String name = this.args[1];
        String type = this.args[2];
        String balance = this.args[3];

        //Executing Variables
        user.SetName(name);
        user.SetType(type);
        user.SetBalance(balance);
        this.userStorage.Create(user);
    }

}