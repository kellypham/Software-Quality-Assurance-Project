#pragma once

#include <string>

#include "../Command.h"
#include "../../models/User.h"
#include "../../storage/UserStorage.h"


/**
 * Implementation of Command Class for the Create actions
 */
class CreateCommand : public Command
{
	public:
	
		/**
		 * Handles command to add credit a users account
		 * @param storage UserStorage object used for validation of user.
		 */
		CreateCommand(UserStorage* storage);


		/**
		 * Prompt and receive inputs of relevant information needed for class execution
		 */
		virtual void GetInputs() override;

		/**
		 * No actions taken in execute.
		 */
		virtual void Execute() override;

		/**
		 * Generate log contests that are too be written to log file.
		 */
		virtual std::string GetLogContents() override;

		virtual std::string PromptUntilNameValid(std::string name);

		virtual std::string PromptUntilTypeValid(std::string name);

	private:
	
		/** UserStorage object for validation */
		UserStorage* userStorage;
		
		/** Name of account user to be created */
		std::string name;
		
		/** Type of account user to be created */
		std::string type;

		/** Credit of account to be created */
		int credit;
};