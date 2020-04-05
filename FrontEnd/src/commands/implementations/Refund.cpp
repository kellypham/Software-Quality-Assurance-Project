#include "Refund.h"
#include <iostream>

const int LOG_USER_NAME_LENGTH = 15;
const int LOG_REFUND_CREDIT_LENGTH = 9;

const int LIMIT_CREDIT = 1000;

Refund::Refund(UserStorage* userStorage)
{
	this->userStorage = userStorage;
}

void Refund::GetInputs()
{
    this->buyerName = PromptUntilStringValid("Buyer name: ", LOG_USER_NAME_LENGTH);
    while(true) {
        try
        {
            User* buyer = this->userStorage->GetByName(buyerName);
            if(buyer->GetType() == UserType::SELL_STANDARD) {
                std::cout << buyerName << " is not a valid buyer" << std::endl;
                this->buyerName = PromptUntilStringValid("Buyer name: ", LOG_USER_NAME_LENGTH);
                continue;
            }
            this->FlushStdIn();
            break; 
        }
        catch(const UserStorageException& e)
        {
            std::cout << "User '" << buyerName << "' not found" << std::endl;
            this->buyerName = PromptUntilStringValid("Buyer name: ", LOG_USER_NAME_LENGTH);
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

    this->refundCredit = PromptUntilNumValueValid("Refund credit: ", LIMIT_CREDIT);
    while(true) {
        User* seller = this->userStorage->GetByName(sellerName);
        if(seller->GetCredit() < refundCredit) {
            std::cout << seller->GetName() << " does not have enough credit" << std::endl;
            this->refundCredit = PromptUntilNumValueValid("Refund credit: ", LIMIT_CREDIT);
            continue;
        }

        // Succesful refund
        this->FlushStdIn();
        break;
    }
}

void Refund::Execute() 
{
}


std::string Refund::GetLogContents()
{
	std::string transactionNo = "05";
	std::string buyerName = PadString(this->buyerName, LOG_USER_NAME_LENGTH);
	std::string sellerName = PadString(this->sellerName, LOG_USER_NAME_LENGTH);
	std::string refundCredit = PadString(std::to_string(this->refundCredit), LOG_REFUND_CREDIT_LENGTH);

	return transactionNo + " "
		+ buyerName + " "
		+ sellerName + " "
		+ refundCredit;
}
