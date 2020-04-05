#include "Advertise.h"
#include <iostream>

const int LOG_USER_NAME_LENGTH = 15;
const int LOG_ITEM_NAME_LENGTH = 19;
const int LOG_DAYS_TO_AUCTION_LENGTH = 3;
const int LOG_MIN_BID_LENGTH = 6;

const int LIMIT_AUCTION_DAYS = 100;
const int LIMIT_CREDIT = 1000;

Advertise::Advertise(ApplicationState* application, UserStorage* userStorage, ItemStorage* itemStorage)
{
    this->application = application;
    this->userStorage = userStorage;
    this->itemStorage = itemStorage;
}

void Advertise::GetInputs()
{
    this -> itemName = PromptUntilStringValid("Enter item name: ", LOG_ITEM_NAME_LENGTH);
    this->daysToAuction = PromptUntilNumValueValid("Number of days to auction:", LIMIT_AUCTION_DAYS);
    this->minimumBid = PromptUntilNumValueValid("Minimum bid: ", LIMIT_CREDIT);
}

std::string Advertise::GetLogContents()
{
	User* currentUser = application->LoggedInUser();

    std::string transactionNo = "03";
	std::string sellerName = PadString(currentUser->GetName(), LOG_USER_NAME_LENGTH);
    std::string itemName = PadString(this->itemName, LOG_ITEM_NAME_LENGTH);
    std::string daysToAuction = PadString(std::to_string(this->daysToAuction), LOG_DAYS_TO_AUCTION_LENGTH);
    std::string minimumBid = PadString(std::to_string(this->minimumBid), LOG_MIN_BID_LENGTH);

    return
		transactionNo + " "
		+ itemName + " "
		+ sellerName + " "
		+ daysToAuction + " "
		+ minimumBid;
}

bool Advertise::IsPermitted(User* user)
{
    return user->GetType() != UserType::BUY_STANDARD;
}