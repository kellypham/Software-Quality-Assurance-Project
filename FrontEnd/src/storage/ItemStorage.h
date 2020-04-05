#pragma once

#include "../models/Item.h"

/**
 * Abstract class definition for accessing Items in long term storage
*/
class ItemStorage
{
	public:
		/**
		 * Function for getting a specific item by its name
		 * @param name name of the item to search for
		 * @exception ItemStorageException when no matching exception is found
		*/
		virtual Item* GetByName(std::string name) = 0;
};