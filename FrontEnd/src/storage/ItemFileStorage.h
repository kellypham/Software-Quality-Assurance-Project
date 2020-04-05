#pragma once

#include <string>

#include "./ItemStorage.h"

/**
 * ItemStorage implementation that reads item information
 *  into from a designated file
 */
class ItemFileStorage : public ItemStorage
{
	public:
		/**
		 * Initializes the file to be read
		 * @param filename Path to the items file to search
		 */
		ItemFileStorage(std::string filename);

		/**
		 * Function for getting a specific item by its name
		 * @param name name of the item to search for
		 * @exception ItemStorageException when no matching exception is found
		*/
		Item* GetByName(std::string name) override;
		
	private:
		/** Path to the Items file */
		std::string filename;
};