package AuctionBack.Commands.Implementation;

public class Refund extends Command{


    private UserStorage userStorage;
    private String[] args;

    public Refund(UserStorage userStorage, String[] args){
        this.userStorage = userStorage;
        this.args = args
    }

    public void Validate(){
        
    }

    public void Execute(){

    }

}