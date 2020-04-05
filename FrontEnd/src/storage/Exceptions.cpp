#include "Exceptions.h"

#include <string>

Exception::Exception(ExceptionType type, std::string message)
{
	this->type = type;
	this->message = message;
}

Exception::~Exception() throw()
{

}

const char* Exception::what() const throw()
{
	return this->message.c_str();
}

int Exception::getErrorNumber() const throw()
{
	return this->type;
}

UserStorageException::UserStorageException(ExceptionType type, std::string message)
	: Exception(type, message)
{
}

ItemStorageException::ItemStorageException(ExceptionType type, std::string message)
	: Exception(type, message)
{
}
