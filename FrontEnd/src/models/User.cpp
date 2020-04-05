#include "User.h"

std::string User::GetName()
{
	return this->name;
}

void User::SetName(std::string name)
{
	this->name = name;
}

UserType User::GetType()
{
	return this->type;
}

void User::SetType(UserType type)
{
	this->type = type;
}

int User::GetCredit()
{
	return this->credit;
}

void User::SetCredit(int credit)
{
	this->credit = credit;
}

bool User::IsAdmin(){
	if (this->type == UserType::ADMIN){
		return true;
	} else {
		return false;
	}
}