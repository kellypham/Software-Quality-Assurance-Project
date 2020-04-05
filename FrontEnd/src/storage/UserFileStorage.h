#pragma once

#include "UserStorage.h"
#include <fstream>
#include <string>

/**
 * UserStorage implementation that accesses users from a file
 */
class UserFileStorage : public UserStorage
{
	public:
		/**
		 * Initializes the file to read user data from
		 * @param filename path to the file containing user data
		 */
		UserFileStorage(std::string filename);

		/**
		 * Finds a user by its name in the User file
		 * @param name Name of user to search for
		 * @exception UserStorageException when no matching user is found
		 */
		User* GetByName(std::string name) override;

	private:
		/** Path to the file to search */
		std::string filename;
};