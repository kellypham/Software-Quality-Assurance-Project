#include "NotFound.h"

#include <iostream>

NotFound::NotFound(std::string commandName)
{
	this->commandName = commandName;
}

void NotFound::Execute()
{
	std::cout << "Command '" << commandName << "' not found" << std::endl;
}

bool NotFound::IsLoginRequired()
{
	return false;
}