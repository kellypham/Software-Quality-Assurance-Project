#include "CommandFactory.h"

#include "../storage/UserFileStorage.h"
#include "../storage/ItemFileStorage.h"
#include "../storage/DailyLogFile.h"

#include "errors/NoCommand.h"
#include "errors/NotFound.h"
#include "errors/MissingPermissions.h"
#include "errors/NotLoggedIn.h"

#include "implementations/Exit.h"
#include "implementations/CreateCommand.h"
#include "implementations/DeleteCommand.h"
#include "implementations/AddCreditCommand.h"
#include "implementations/Login.h"
#include "implementations/Logout.h"
#include "implementations/Advertise.h"
#include "implementations/Bid.h"
#include "implementations/Refund.h"

CommandFactory::CommandFactory(ApplicationState* application, UserStorage* userStorage, ItemStorage* itemStorage, DailyLog* log)
{
	this->application = application;
	this->options = options;

	this->userStorage = userStorage;
	this->itemStorage = itemStorage;
	this->dailyLog = log;
}

CommandFactory::~CommandFactory()
{
}

Command* CommandFactory::Create(std::string input)
{
	Command* command = NULL;

	if (input == "")
	{
		command = new NoCommand();
	}
	else if (input == "login")
	{
		command = new Login(application, userStorage);
	}
	else if (input == "logout")
	{
		command = new Logout(application, dailyLog);
	}
	else if (input == "create")
	{
		command = new CreateCommand(userStorage);
	}
	else if (input == "delete")
	{
		command = new DeleteCommand(application, userStorage);
	}
	else if (input == "addcredit")
	{
		command = new AddCreditCommand(application, userStorage);
	}
	else if (input == "advertise")
	{
		command = new Advertise(application, userStorage, itemStorage);
	}
	else if (input == "bid")
	{
		command = new Bid(application, userStorage, itemStorage);
	}
	else if (input == "refund")
	{
		command = new Refund(userStorage);
	}
	else if (input == "exit")
	{
		command = new Exit(application);
	}
	else
	{
		command = new NotFound(input);
	}

	return PermissionCheck(command);
}

Command* CommandFactory::PermissionCheck(Command* command)
{
	if (command->IsLoginRequired() && !application->IsLoggedIn())
	{
		delete command;
		return new NotLoggedIn();
	}

	User* user = application->LoggedInUser();

	if (command->IsPermitted(user))
	{
		return command;
	}
	else
	{
		delete command;
		return new MissingPermissions();
	}
}
