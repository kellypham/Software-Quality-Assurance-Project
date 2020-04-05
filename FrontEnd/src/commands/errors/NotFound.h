#pragma once
#include <string>

#include "../Command.h"

/**
 * A command to run when the user enters a command that doesn't exist
 */
class NotFound : public Command
{
	public:
		/**
		 * Initializes the name of the command the user typed
		 * @param commandName Name of the command that ws not found
		 */
		NotFound(std::string commandName);

		/**
		 * Outputs an error message specifying that the requested command
		 *  doesn't exist
		*/
		void Execute() override;

		/**
		 * Returns false; this command doesn't require that the user be logged in
		 * @return false
		 */
		bool IsLoginRequired() override;
		
	private:
		/** Name of the command the user entered */
		std::string commandName;	
};