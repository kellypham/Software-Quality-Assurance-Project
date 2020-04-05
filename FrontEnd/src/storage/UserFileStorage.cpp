#include "UserFileStorage.h"
#include "Exceptions.h"

#include <exception>
#include <iostream>
#include <fstream>
#include <string>

const int ENTRY_LENGTH = 28;
const int NAME_LENGTH = 15;
const int TYPE_LENGTH = 2;
const int BALANCE_LENGTH = 9;

UserFileStorage::UserFileStorage(std::string filename)
{
	this->filename = filename;
}

UserType TranslateType(std::string type)
{
	if (type == "AA")
		return UserType::ADMIN;
	else if (type == "FS")
		return UserType::FULL_STANDARD;
	else if (type == "BS")
		return UserType::BUY_STANDARD;
	else if (type == "SS")
		return UserType::SELL_STANDARD;
}

User* UserFileStorage::GetByName(std::string searchForName)
{
	User* user = NULL;
	std::ifstream file;

	std::string name;
	std::string type;
	int credit;
	std::string eof;

	int entryNumber = 0;

	file.open(this->filename);

	while (!file.eof())
	{
		entryNumber++;
		
		file >> name;
		file >> type;
		file >> credit;

		if (name.compare(searchForName) == 0)
		{
			file.close();

			UserType typeEnum = TranslateType(type);
			user = new User();

			user->SetName(name);
			user->SetType(typeEnum);
			user->SetCredit(credit);

			break;
		}
	}

	file.close();

	if (user == NULL)
	{
		throw(UserStorageException(ExceptionType::NOT_FOUND, "User '"+searchForName+"' not found"));
	}
	else
	{
		return user;
	}
}