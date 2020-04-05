#pragma once
#include <string>

#include "../models/User.h"

/**
 * Abstract class definition for accessing User objects from
 *  long term storage
 */
class UserStorage
{
	public:
		/**
		 * Query long term storage for a user by its name
		 * @param name Name of the user to search for
		 * @exception UserStorageException when no matching user is found
		 */
		virtual User* GetByName(std::string name) = 0;
};