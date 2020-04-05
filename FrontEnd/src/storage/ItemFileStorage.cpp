#include "ItemFileStorage.h"
#include "Exceptions.h"
#include "../models/Item.h"

#include <fstream>
#include <string>

ItemFileStorage::ItemFileStorage(std::string filename)
{
	this->filename = filename;
}

Item* ItemFileStorage::GetByName(std::string name)
{
	Item* item = NULL;
	std::ifstream file;

	std::string itemName;
	std::string seller;
	std::string highestBidder;
	int daysRemaining;
	int highestBid;

	int entryNumber = 0;

	file.open(this->filename);

	while (!file.eof())
	{
		entryNumber++;
		
		file >> itemName;
		file >> seller;
		file >> highestBidder;
		file >> daysRemaining;
		file >> highestBid;

		if (itemName.compare(name) == 0)
		{
			file.close();

			item = new Item();

			item->SetName(itemName);
			item->SetSellerName(seller);
			item->SetHighestBidderName(highestBidder);
			item->SetDaysRemaining(daysRemaining);
			item->SetHighestBid(highestBid);

			break;
		}
	}

	file.close();

	if (item == NULL)
	{
		throw(ItemStorageException(ExceptionType::NOT_FOUND, "User '"+name+"' not found"));
	}
	else
	{
		return item;
	}
}