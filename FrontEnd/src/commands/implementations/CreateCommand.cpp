#include "CreateCommand.h"
#include "../../storage/Exceptions.h"
#include "../Command.h"

#include <iostream>

const int LOG_NAME_LENGTH = 15;
const int LOG_TYPE_LENGTH = 2;
const int LOG_CREDIT_LENGTH = 9;

CreateCommand::CreateCommand(UserStorage* storage)
{
	this->userStorage = storage;
}

void CreateCommand::GetInputs()
{
	int n; 

	this->FlushStdIn();

	this->name = PromptUntilNameValid("Name: ");

	try
	{
		User* user = this->userStorage->GetByName(name);
		std::cout << "User '" << name << "' already exists" << std::endl;
		GetInputs();
	}
	catch(const UserStorageException& e)
	{
		this->type = PromptUntilTypeValid("Type: ");
		this->credit = PromptUntilValidCreate("Credit: ");
		this->FlushStdIn();
	}

}

void CreateCommand::Execute()
{
	//No actions taken aside from adding transaction to log file
}

std::string CreateCommand::GetLogContents()
{
	std::string transactionNo = "01";
	std::string name = PadString(this->name, LOG_NAME_LENGTH);
	std::string type = PadString(this->type, LOG_TYPE_LENGTH);
	std::string credit = PadString(std::to_string(this->credit), LOG_CREDIT_LENGTH);

	return transactionNo + " "
		+ name + " "
		+ type + " "
		+ credit;
}

std::string CreateCommand::PromptUntilNameValid(std::string prompt)
{
	bool valid;

	std::cout << prompt;
	std::cin >> name;

	if (name.size() <= 15){
		valid = true;
	} else {
		valid = false;
	}

	while (valid == false)
	{
		std::cout << "Username must be less than 15 characters" << std::endl;
		std::cout << prompt;

		FlushStdIn();

		std::cin >> name;
		
		if (name.size() <= 15){
			valid = true;
		} else {
			valid = false;
		}
	}

	return name;
}

std::string CreateCommand::PromptUntilTypeValid(std::string prompt)
{
	bool valid;
	std::string type;
	std::cout << prompt;
	std::cin >> type;

	if (type == "AA" || type == "FS" || type == "SS" || type == "BS"){
		valid = true;
	} else {
		valid = false;
	}

	while (valid == false)
	{
		std::cout << "Invalid User Type" << std::endl;
		std::cout << prompt;

		FlushStdIn();

		std::cin >> type;

		if (type == "AA" || type == "FS" || type == "SS" || type == "BS"){
			valid = true;
		} else {
			valid = false;
		}
	}

	return type;

}