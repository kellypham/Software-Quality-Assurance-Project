#pragma once

#include <string>

#include "../Command.h"
#include "../../models/User.h"
#include "../../storage/UserStorage.h"
#include "../../ApplicationState.h"

/**
 * Implementation of Command Class for the Delete actions
 */
class DeleteCommand : public Command
{
	public:
	
		/**
		 * Handles command to add credit a users account
         * @param application State of the application
		 * @param storage UserStorage object used for validation of user.
		 */
		DeleteCommand(ApplicationState* application, UserStorage* storage);

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

		
		/**
		 * Check if Logged in User matches account attempting to delete.
		 */
		std::string PromptUntilValidDelete(std::string prompt, std::string name);

		/**
		 * Generate log contests that are too be written to log file.
		 */
		bool IsPermitted(User* user) override;

	private:
		/** Application state */
        ApplicationState* application;

		/** UserStorage object for validation */
		UserStorage* userStorage;
		
		/** Name of account user to be deleted */
		std::string name;
		
		/** Type of account user to be deleted */
		std::string type;
		
		/** Credit of account to be deleted */
		int credit;
};