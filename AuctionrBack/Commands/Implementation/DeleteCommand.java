package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.UserStorage;

//Class for Delete Command
public class DeleteCommand extends Command {
    
    private String[] args;
    private UserStorage userStorage;

    public DeleteCommand(String[] args){
		super(args);
        this.args = args;
    }

    /* Function to check if the transaction code is correct 
    * and does not have bugs/errors
    */
    public void Validate() throws Exception{
    
        //If there are too many arguments for Delete Command
        if (this.args.length != 4){
            throw new Exception("Error: The arguments doesn't have the required length" + this.args.length);
        }

    }

    /* The Execute Command to update the transaction that 
    *  updates the code with the user
    */
    public void Execute() throws Exception{
        //Args Variable
        String name = this.args[1];
        
        //Finding the User
        User user = this.userStorage.GetByName(name);

        //Deleting the variable
        user = null;
    }

}
