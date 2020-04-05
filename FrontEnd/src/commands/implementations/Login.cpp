#include "Login.h"
#include "../../storage/Exceptions.h"

#include <iostream>

Login::Login(ApplicationState* application, UserStorage* storage)
{
	this->application = application;
	this->userStorage = storage;
}

void Login::GetInputs()
{
	if (application->IsLoggedIn())
	{
		std::cout << "Already logged in" << std::endl;
		return;
	}

	this->FlushStdIn();

	std::string username;

	std::cout
		<< "Username: ";

	std::cin >> username;

	try
	{
		User* user = this->userStorage->GetByName(username);
		this->user = user;
		this->FlushStdIn();
	}
	catch(const UserStorageException& e)
	{
		std::cout << "User '" << username << "' not found" << std::endl;
		GetInputs();
	}
}

void Login::Execute()
{
	if (!application->IsLoggedIn())
	{
		this->application->SetLoggedInUser(this->user);
		std::cout << "Welcome, " << this->user->GetName() << std::endl;
	}
	
}

bool Login::IsLoginRequired()
{
	return false;
}