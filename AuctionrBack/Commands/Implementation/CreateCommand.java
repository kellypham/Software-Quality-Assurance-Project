package AuctionBack.Commands.Implementation;

public class CreateCommand extends Command{

    private UserStorage userStorage;
    private String[] args;


    public CreateCommand(UserStorage userStorage, String[] args){
        //Constructor variables
        this.userStorage = userStorage;
        this.args = args;
    }


    public void Validate() throws MyException{
        //The amount of credits is greater than 999,999 
        String balance = this.args[3];
        if (balance > 999999){
            throw new MyException(balance);
            System.out.println("Error: The amount of Credit inputted is greater than 999,999");
        }

        //If there are too many arguments for Create Command
        if (this.args.length() != 4){
            throw new MyException(this.args.length);
            System.out.println("Error: The arguments doesnt have the required length");
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