#include "Exit.h"

Exit::Exit(ApplicationState* application)
{
	this->application = application;
}

void Exit::Execute()
{
	this->application->Exit();
}

bool Exit::IsLoginRequired()
{
	return false;
}