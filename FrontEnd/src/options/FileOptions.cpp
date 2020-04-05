#include "FileOptions.h"

#include <iostream>
#include <fstream>
#include <fstream>
#include <cstring>
#include <errno.h>

FileOptions::FileOptions()
{
}

void VerifyFile(std::string filePath, std::string mode)
{
	FILE* file = fopen(filePath.c_str(), "r");
	
	if (errno != 0)
	{
		throw "Can not open file '" + filePath
			+ "': "
			+ strerror(errno)
			+ "\nContact an administrator";
	}

	fclose(file);
}

void FileOptions::Validate()
{
	VerifyFile(AccountsFile(), "r");
	VerifyFile(ItemsFile(), "r");

	//Log file is tested differently because it may not exist at startup
	std::ofstream logfile;
	logfile.open(DailyLogFile(), std::fstream::out | std::fstream::app);

	if (!logfile.good())
	{
		throw "Can not open log file '"
			+ DailyLogFile()
			+ "'\n"
			+ "Contact an administrator\n";
	}

	logfile.close();
}

void FileOptions::LoadOptions(std::string filename)
{
	std::fstream file(filename);

	std::string key;
	std::string value;
	while (!file.eof())
	{
		file >> key;
		file >> value;

		this->configuration[key] = value;
	}

	if (!IsKeyDefined(FILECONFIG_KEY_ACCOUNTS_FILE))
	{
		std::cout
			<< "Configuration error: "
			<< FILECONFIG_KEY_ACCOUNTS_FILE
			<< " not defined" << std::endl;
	}
	else if (!IsKeyDefined(FILECONFIG_KEY_ITEMS_FILE))
	{
		std::cout
			<< "Configuration error: "
			<< FILECONFIG_KEY_ITEMS_FILE
			<< " not defined" << std::endl;
	}
	else if (!IsKeyDefined(FILECONFIG_KEY_LOG_FILE))
	{
		std::cout
			<< "Configuration error: "
			<< FILECONFIG_KEY_LOG_FILE
			<< " not defined" << std::endl;
	}
}

bool FileOptions::IsKeyDefined(std::string key)
{
	return this->configuration.find(key) != this->configuration.end();
}

std::string FileOptions::AccountsFile()
{
	return this->configuration[FILECONFIG_KEY_ACCOUNTS_FILE];
}

std::string FileOptions::ItemsFile()
{
	return this->configuration[FILECONFIG_KEY_ITEMS_FILE];
}

std::string FileOptions::DailyLogFile()
{
	return this->configuration[FILECONFIG_KEY_LOG_FILE];
}