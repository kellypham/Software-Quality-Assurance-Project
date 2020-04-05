#include "NotLoggedIn.h"

#include <iostream>

NotLoggedIn::NotLoggedIn()
{
}

void NotLoggedIn::Execute()
{
	std::cout << "You must be logged in to use that command" << std::endl;
}

bool NotLoggedIn::IsLoginRequired()
{
	return false;
}