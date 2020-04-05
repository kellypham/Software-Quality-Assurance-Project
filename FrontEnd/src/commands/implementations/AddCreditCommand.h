#pragma once

#include <string>

#include "../Command.h"
#include "../../models/User.h"
#include "../../storage/UserStorage.h"
#include "../../ApplicationState.h"

/**
 * Implementation of Command Class for the AddCredit actions
 */
class AddCreditCommand : public Command
{
	public:
		/**
		 * Handles command to add credit a users account
         * @param application State of the application
		 * @param storage UserStorage object used for validation of user.
		 */
		AddCreditCommand(ApplicationState* application, UserStorage* storage);

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

	private:
		User* GetUserInput();
		int GetCreditInput();

		/** Application state */
        ApplicationState* application;
		
		/** UserStorage object for validation */
		UserStorage* userStorage;

		/** Name of account user to be altered */
		std::string name;
		
		/** Type of account user to be altered */
		std::string type;

		/** Credit to be added to user account */
		int credit;
};