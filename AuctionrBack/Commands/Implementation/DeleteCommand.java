package AuctionBack.Commands.Implementation;

public class DeleteCommand extends Command{

    private UserStorage userStorage;
    private String[] args;


    public DeleteCommand(UserStorage userStorage, String[] args){
        this.userStorage = userStorage;
        this.args = args;
    }

    /* Function to check if the transaction code is correct 
    * and does not have bugs/errors
    */
    public void Validate() throws MyException{
    
        //If there are too many arguments for Delete Command
        if (this.args.length() != 4){
            throw new MyException(this.args.length);
            System.out.println("Error: The arguments doesn't have the required length");
        }

    }

    /* The Execute Command to update the trasnaction that 
    *  updates the code with the user
    */
    public void Execute(){
        //Args Variable
        String name = this.args[1];
        
        //Finding the User
        User user = this.userStorage.GetByName(name);

        //Deleting the variable
        user = null;
    }

}