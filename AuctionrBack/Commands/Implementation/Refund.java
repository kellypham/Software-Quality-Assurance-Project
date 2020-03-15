package AuctionrBack.Commands.Implementation;

import AuctionrBack.Commands.Command;
import AuctionrBack.Models.*;
import AuctionrBack.Storage.UserStorage;
import AuctionrBack.Storage.Exceptions.*;

//Class Refund
public class Refund extends Command {
    private String[] args;
    private UserStorage userStorage;

    //Constructor
    public Refund(String[] args){
		  super(args);
      this.args = args;
    }

    //Valdidating the Refund arguments
    public void Validate() throws MyException, UserNotFoundException{

        //Buyer and Seller Username
        String buyer = this.args[1];
        String seller = this.args[2];

        //Getting the User of the strings
        User buyerUser = this.userStorage.GetByName(buyer);
        User sellerUser = this.userStorage.GetByName(seller);

        //Validiating that the number of arguments is correct
        if(this.args.length != 4){
          throw new MyException("Error: The arguments doesn't have the required length ");
        }
        //checking if buyer's and user's are a valid user will throw an exception
        this.userStorage.GetByName(buyer);
        this.userStorage.GetByName(seller);

    }

    //Executing the Refund arguments
    public void Execute() throws UserNotFoundException{
      //Getting the Strings 
      String buyerString = this.args[1];
      String sellerString = this.args[2];

      int credits = Integer.parseInt(this.args[3]);

      //Getting the User of the strings
      User buyerUser = this.userStorage.GetByName(buyerString);
      User sellerUser = this.userStorage.GetByName(sellerString);

      //Getting the Buyer and Seller Credit
      int buyerCredit = buyerUser.GetCredit();
      int sellerCredit = sellerUser.GetCredit();

      //Calculations
      buyerCredit += credits;
      sellerCredit -= credits;

      //Updating the credits
      buyerUser.SetCredit(buyerCredit);
      sellerUser.SetCredit(sellerCredit); 

      //Update buyer and seller
      this.userStorage.Update(buyerUser);
      this.userStorage.Update(sellerUser);
    }

}
