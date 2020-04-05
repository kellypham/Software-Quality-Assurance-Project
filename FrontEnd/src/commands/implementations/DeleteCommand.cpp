#include "DeleteCommand.h"
#include "../../storage/Exceptions.h"

#include <iostream>

const int LOG_NAME_LENGTH = 15;
const int LOG_TYPE_LENGTH = 2;
const int LOG_CREDIT_LENGTH = 9;

DeleteCommand::DeleteCommand(ApplicationState* application, UserStorage* storage)
{
	this->application = application;
	this->userStorage = storage;
}

void DeleteCommand::GetInputs()
{
	User* user;

	this->FlushStdIn();

	try
	{
		this->name = PromptUntilValidDelete("Name: ", this->application->LoggedInUser()->GetName());
		user = this->userStorage->GetByName(name);
		this->name = name;;
		this->FlushStdIn();
	}
	catch(const UserStorageException& e)
	{
		std::cout << "User '" << name << "' not found" << std::endl;
		GetInputs();
	}
	
	this->type = UserTypeToString(user->GetType());
	this->credit = user->GetCredit();
}

void DeleteCommand::Execute()
{
	//No actions taken aside from adding transaction to log file
}

std::string DeleteCommand::GetLogContents()
{
	std::string transactionNo = "02";
	std::string name = PadString(this->name, LOG_NAME_LENGTH);
	std::string type = PadString(this->type, LOG_TYPE_LENGTH);
	std::string credit = PadString(std::to_string(this->credit), LOG_CREDIT_LENGTH);

	return transactionNo + name + type + credit;
}

std::string DeleteCommand::PromptUntilValidDelete(std::string prompt, std::string logged_name){

	bool valid;
	std::string user_name;
	std:: cout << prompt;
	std:: cin >> user_name;

	if (user_name == logged_name){
		valid = false;
	} else {
		valid = true;
	}

	while (valid ==  false){
		std::cout << "Unable to delete your own account." << std::endl;
		std::cout << prompt;

		FlushStdIn();

		std::cin >> user_name;

		if (user_name == logged_name){
			valid = false;
		} else {
			valid = true;
		}
	}

	return user_name;
}

bool DeleteCommand::IsPermitted(User* user){
	return (user->IsAdmin());
}