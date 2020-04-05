#include "EnvOptions.h"

#include <string>

void EnvOptions::Validate()
{
	if (AccountsFile() == "")
	{
		throw "Environment variable " + std::string(ENV_ACCOUNTS_FILE) + " not set";
	}
	else if (ItemsFile() == "")
	{
		throw "Environment variable " + std::string(ENV_ITEMS_FILE) + " not set";
	}
	else if (DailyLogFile() == "")
	{
		throw "Environment variable " + std::string(ENV_LOG_FILE) + " not set";
	}
}

std::string EnvOptions::AccountsFile()
{
	return std::getenv(ENV_ACCOUNTS_FILE);
}

std::string EnvOptions::ItemsFile()
{
	return std::getenv(ENV_ITEMS_FILE);
}

std::string EnvOptions::DailyLogFile()
{
	return std::getenv(ENV_LOG_FILE);
}