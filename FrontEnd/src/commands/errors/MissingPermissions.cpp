#include "MissingPermissions.h"

#include <iostream>

MissingPermissions::MissingPermissions()
{
}

void MissingPermissions::Execute()
{
	std::cout << "You do not have permission to perform that action" << std::endl;
}