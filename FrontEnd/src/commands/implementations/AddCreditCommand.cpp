#include "AddCreditCommand.h"
#include "../../storage/Exceptions.h"
#include "../Command.h"

#include <iostream>

const int LOG_NAME_LENGTH = 15;
const int LOG_TYPE_LENGTH = 2;
const int LOG_CREDIT_LENGTH = 9;

const int MIN_CREDIT = 1;
const int MAX_CREDIT = 1000;

AddCreditCommand::AddCreditCommand(ApplicationState* application, UserStorage* storage)
{
	this->application = application;
	this->userStorage = storage;
}

void AddCreditCommand::GetInputs()
{
	std::string n; 
	std::string type;
	User* user;

	this->FlushStdIn();

	if (this->application->LoggedInUser()->IsAdmin())
	{
		user = GetUserInput();
	}
	else
	{
		user = application->LoggedInUser();
	}

	this->name = user->GetName();
	this->type = UserTypeToString(user->GetType());

	this->credit = GetCreditInput();

	if (user != application->LoggedInUser())
	{
		delete user;
	}
}

User* AddCreditCommand::GetUserInput()
{
	User* user;
	std::string name;

	std::cout << "Name: ";
	std::cin >> name;

	try
	{
		user = this->userStorage->GetByName(name);
		return user;
	}
	catch(const UserStorageException& e)
	{
		std::cout << "User '" << name << "' not found" << std::endl;
		return GetUserInput();
	}
}

int AddCreditCommand::GetCreditInput()
{
	int amount = PromptUntilNumeric("Credit: ");

	if (amount < MIN_CREDIT || amount > MAX_CREDIT)
	{
		std::cout
			<< "Amount must be between "
			<< MIN_CREDIT
			<<  " and "
			<< MAX_CREDIT
			<< std::endl;

		return GetCreditInput();
	}

	return amount;
}

void AddCreditCommand::Execute()
{
	//No actions taken aside from adding transaction to log file
}

std::string AddCreditCommand::GetLogContents()
{
	std::string transactionNo = "06";
	std::string name = PadString(this->name, LOG_NAME_LENGTH);
	std::string type = PadString(this->type, LOG_TYPE_LENGTH);
	std::string credit = PadString(std::to_string(this->credit), LOG_CREDIT_LENGTH);

	return transactionNo + " " + name + " " + type + " " + credit;
}