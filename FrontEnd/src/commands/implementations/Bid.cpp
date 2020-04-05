#include "Bid.h"
#include <iostream>

const int LOG_USER_NAME_LENGTH = 15;
const int LOG_ITEM_NAME_LENGTH = 19;
const int LOG_MIN_BID_LENGTH = 6;

const int LIMIT_CREDIT = 1000;

Bid::Bid(ApplicationState* application, UserStorage* userStorage, ItemStorage* itemStorage)
{
    this->application = application;
    this->userStorage = userStorage;
    this->itemStorage = itemStorage;
}

void Bid::GetInputs()
{
    this->itemName = PromptUntilStringValid("Item name: ", LOG_ITEM_NAME_LENGTH);
    while(true) {
        try
        {
            Item* item = this->itemStorage->GetByName(itemName);
            this->FlushStdIn();
            break;
        }
        catch(const ItemStorageException& e)
        {
            std::cout << "Item '" << itemName << "' not found" << std::endl;
            this->itemName = PromptUntilStringValid("Item name: ", LOG_ITEM_NAME_LENGTH);
        }
    }
    
    this->sellerName = PromptUntilStringValid("Seller name: ", LOG_USER_NAME_LENGTH);
    while(true) {
        try
        {
            User* seller = this->userStorage->GetByName(sellerName);
            if(seller->GetType() == UserType::BUY_STANDARD) {
                std::cout << sellerName << " is not a valid seller" << std::endl;
                this->sellerName = PromptUntilStringValid("Seller name: ", LOG_USER_NAME_LENGTH);
                continue;
            } 
            this->FlushStdIn();
            break;
        }
        catch(const UserStorageException& e)
        {
            std::cout << "User '" << sellerName << "' not found" << std::endl;
            this->sellerName = PromptUntilStringValid("Seller name: ", LOG_USER_NAME_LENGTH);
        }
    }

    this->newBid = PromptUntilNumValueValid("New bid: ", LIMIT_CREDIT);
    while(true) {
        Item* item = this->itemStorage->GetByName(itemName);
        if(!this->application->LoggedInUser()->IsAdmin()) {
            if(newBid > item -> GetHighestBid() * 1.05 && this -> sellerName == item -> GetSellerName()) {
                // Succesful bid
                this->FlushStdIn();
                break;
            } 
            else {
                std::cout << "Bid value must be above previous bid by 5% unless Admin account" << std::endl;
                this->newBid = PromptUntilNumValueValid("New bid: ", LIMIT_CREDIT);
                continue;
            }
        }
        this->FlushStdIn();
        break;
    }

}

void Bid::Execute() 
{
    
}

std::string Bid::GetLogContents()
{
	User* currentUser = this->application->LoggedInUser();

    std::string transactionNo = "04";
    std::string itemName = PadString(this->itemName, LOG_ITEM_NAME_LENGTH);
    std::string sellerName = PadString(this->sellerName, LOG_USER_NAME_LENGTH);
	std::string bidderName = PadString(currentUser->GetName(), LOG_USER_NAME_LENGTH);
    std::string newBid = PadString(std::to_string(this->newBid), LOG_MIN_BID_LENGTH);

    return transactionNo + " "
		+ itemName + " " 
		+ sellerName + " " 
		+ bidderName + " "
		+ newBid;
}

bool Bid::IsPermitted(User* user)
{
    return user->GetType() != UserType::SELL_STANDARD;
}
