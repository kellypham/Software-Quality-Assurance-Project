#include "ApplicationState.h"

ApplicationState::ApplicationState()
{
	this->isCompleted = false;
	this->loggedInUser = NULL;
}

ApplicationState::~ApplicationState()
{
	if (this->IsLoggedIn())
	{
		delete this->loggedInUser;
	}
}

void ApplicationState::SetLoggedInUser(User* user)
{
	if (user == NULL)
	{
		delete this->loggedInUser;
	}
	
	this->loggedInUser = user;
}

bool ApplicationState::IsLoggedIn()
{
	return this->loggedInUser != NULL;
}

User* ApplicationState::LoggedInUser()
{
	return this->loggedInUser;
}

void ApplicationState::Exit()
{
	this->isCompleted = true;
}

bool ApplicationState::IsCompleted()
{
	return this->isCompleted;
}