#include "Logout.h"

#include <iostream>

Logout::Logout(ApplicationState* application, DailyLog* log)
{
	this->application = application;
	this->dailyLog = log;
}

void Logout::Execute()
{
	if (application->IsLoggedIn())
	{
		application->SetLoggedInUser(NULL);

		dailyLog->WriteChanges();
		dailyLog->ClearBuffer();
	}
	else
	{
		std::cout << "Not logged in" << std::endl;
	}
}