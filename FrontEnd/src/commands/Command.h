#pragma once
#include <iostream>
#include <string>

#include "../models/User.h"

/**
 * Base class for all actions the user takes in the system.
 */
class Command
{
	public:
		/** Function for the Command to prompt and validate user input */
		virtual void GetInputs() {}
		/**
		 * Function for the Command to perform any actions it needs to do with
		 *  the data it was given
		 */
		virtual void Execute() {}
		/**
		 * Get the entry to the daily log file from the command.
		 * An empty string will be ignored.
		 * @return Log file entry for this command.
		 */
		virtual std::string GetLogContents() { return ""; }

		/**
		 * Function to check if a user has sufficient permissions to run this command.
		 * @param user User to validate permissions for
		 * @return Whether the user is allowed to execute this command
		 */
		virtual bool IsPermitted(User* user) { return true; }
		/**
		 * Function flagging whether a user must be logged in to run this command
		 * @return Whether this command requires that the user be logged in
		 */
		virtual bool IsLoginRequired() { return true; }

	protected:
		/**
		 * Utility function. Adds spaces to the end of a string until
		 * that string's length reaches size
		 * 
		 * @return A copy of s, with spaces added on the end
		 */
		std::string PadString(std::string s, int size);
		/**
		 * Utility function, clears the stdin buffer and resets
		 *  its error state
		 */
		void FlushStdIn();
		/**
		 * Utility function, will read a value from stdin, and will
		 *  re-prompt the input until an integer has been given.
		 */
		int PromptUntilNumeric(std::string prompt);
		
		/**
		 * Utility function, will read a value from stdin, and will
		 *  re-prompt the input until an integer has been given 
		 *  AND integer is within bounds.
		 */
		int PromptUntilValidCreate(std::string prompt);

		/**
		 * Utility function, will read a value from stdin, and will
		 *  re-prompt the input until a valid user or item has been given
		 *  limit: characted limit for the input string
		 */
		std::string PromptUntilStringValid(std::string prompt, int limit);

		/**
		 * Utility function, will read a value from stdin, and will
		 *  re-prompt the input until a valid numeric value has been given
		 *  limit: characted limit for the input number 
		 */
		int PromptUntilNumValueValid(std::string prompt, int limit);

		std::string UserTypeToString(UserType type);
};