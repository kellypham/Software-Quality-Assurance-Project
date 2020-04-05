/**
 * Auctionr: A CSCI3060 Application
 * The command line auction application
 * 
 * This application handles auctioning of items, which can be posted for auction and bid on.
 * 
 * To run the application, open a command line and run the executable for the application
 * 
 * It reads the input file UserFile.txt for the user accounts and balance
 * It reads the input file ItemFile.txt for the list of items up for auction and the
 *   current bids on that item.
 * Runtime options are read from Options.txt
 * After logging out, any transactions the user submit are appended to LogFile.txt 
 * 
 * @author Anthony Kouroupis
 * @author Alexander Minz
 * @author Omer Baskurt
*/

#include <stdlib.h>
#include <iostream>
#include <stdio.h>
#include <string>

#include "storage/UserFileStorage.h"
#include "storage/ItemFileStorage.h"
#include "storage/DailyLogFile.h"

#include "options/Options.h"
#include "options/FileOptions.h"
#include "commands/CommandFactory.h"
#include "storage/DailyLogFile.h"

using namespace std;

ApplicationState application;
Options* options;

void SetOptions(int argc, char** argv)
{
	std::string optionsFile;

	if (argc == 2)
	{
		optionsFile = argv[1];
	}
	else
	{
		optionsFile = "Options.txt";
	}
	
	FileOptions* opt = new FileOptions();
	opt->LoadOptions(optionsFile);

	options = opt;
}

int main(int argc, char** argv)
{
	SetOptions(argc, argv);

	try
	{
		options->Validate();
	}
	catch(std::string error)
	{
		std::cout << error;
		exit(1);
	}
	
	application = ApplicationState();
	
	UserFileStorage* userStorage = new UserFileStorage(options->AccountsFile());
	ItemFileStorage* itemStorage = new ItemFileStorage(options->ItemsFile());
	DailyLogFile* log = new DailyLogFile(options->DailyLogFile());

	CommandFactory commandFactory(&application, userStorage, itemStorage, log);
	string input = "";
	
	cout << "Welcome to the CSCI3060 Auction App" << endl;

	while (!application.IsCompleted())
	{
		cout << "Command: ";

		cin >> input;
		
		Command* command = commandFactory.Create(input);

		command->GetInputs();
		command->Execute();
		std::string logContents = command->GetLogContents();
		
		delete command;

		if (logContents != "")
		{
			log->AddEntry(logContents);
		}

		cin.clear();
		fflush(stdin);
	}

	delete options;
	delete userStorage;
	delete itemStorage;
	delete log;

	return 0;
}