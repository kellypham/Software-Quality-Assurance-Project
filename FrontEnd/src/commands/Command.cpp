#include "Command.h"
#include <iostream>
#include <limits>

std::string Command::PadString(std::string string, int size)
{
	int i;
	std::string result = "";
	result.reserve(string.length() + size);

	for (i=0; i<string.length(); i++)
	{
		result += string[i];
	}

	for (; i < size; i++)
	{
		result += " ";
	}
	return result;
}

void Command::FlushStdIn()
{
	//Clear state vars
	std::cin.clear();
	//Clear the input buffer
	std::cin.ignore(256,'\n');
	fflush(stdin);
}

int Command::PromptUntilNumeric(std::string prompt)
{
	int n;
	std::cout << prompt;
	std::cin >> n;

	while(std::cin.fail())
	{
        std::cout << "Input must be numeric" << std::endl;
		std::cout << prompt;

       FlushStdIn();

        std::cin >> n;
    }

	return n;
}

int Command::PromptUntilValidCreate(std::string prompt)
{
	int n;
	bool valid;

	std::cout << prompt;
	std::cin >> n;

	if (n <= 999999 & n > 0){
		valid = true;
	}else{
		valid = false;
	}

	while(std::cin.fail())
	{
        std::cout << "Input must be numeric" << std::endl;
		std::cout << prompt;

       FlushStdIn();

        std::cin >> n;
    }

	while(valid == false)
	{
        std::cout << "Input must be in range" << std::endl;
		std::cout << prompt;

       FlushStdIn();

        std::cin >> n;

		if (n <= 999999 & n > 0){
			valid = true;
		}else{
			valid = false;
		}
    }

	return n;
}

std::string Command::PromptUntilStringValid(std::string prompt, int limit)
{
	bool valid;
	std::string itemName;
	std::cout << prompt;
	std::cin >> itemName;

	if (itemName.size() <= limit && itemName.size() > 0){
		valid = true;
	} else {
		valid = false;
	}

	while (valid == false)
	{
        
		std::cout << "Input must be less than " << limit + 1 <<" characters" << std::endl;
		std::cout << prompt;

		FlushStdIn();

		std::cin >> itemName;
		
		if (itemName.size() <= limit){
			valid = true;
		} else {
			valid = false;
		}
	}

	return itemName;
}

int Command::PromptUntilNumValueValid(std::string prompt, int limit) {
	int n;
	bool valid;
	
	std::cout << prompt;
	std::cin >> n;

	while(std::cin.fail())
	{
        std::cout << "Input must be numeric" << std::endl;
		std::cout << prompt;

       FlushStdIn();

        std::cin >> n;
    }

    if (n <= limit & n > 0){
		valid = true;
	} else{
		valid = false;
	}

	while(valid == false)
	{
        std::cout << "Input must be in range" << std::endl;
		std::cout << prompt;

       FlushStdIn();

        std::cin >> n;

		if (n <= limit & n > 0){
			valid = true;
		}else{
			valid = false;
		}
    }

	return n;
}

std::string Command::UserTypeToString(UserType type){
	if (type == UserType::ADMIN){
		return "AA";
	} else if (type == UserType::FULL_STANDARD) {
		return "FS";
	} else if (type == UserType::BUY_STANDARD) {
		return "BS";
	} else if (type == UserType::SELL_STANDARD){
		return "SS";
	}
}