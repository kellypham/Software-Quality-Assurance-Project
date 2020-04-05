#pragma once
#include "../Command.h"
#include "../../ApplicationState.h"
#include "../../storage/UserStorage.h"

/** Command to log in to the system */
class Login : public Command
{
	public:
		/**
		 * Initializes the application state to modify and the user
		 *  storage from which to search for the user
		 */
		Login(ApplicationState* application, UserStorage* userStorage);

		/**
		 * Reads a username from the user. Will re-prompt for input
		 *  until the user has input an existing username
		 */
		void GetInputs() override;
		/**
		 * Updates the application state to be logged in as the user matching
		 *  the username given in GetInputs()
		 */
		void Execute() override;

		/**
		 * This command doesn't require that the user be logged in
		 * @return false
		 */
		bool IsLoginRequired() override;

	private:
		/** User found matching the username entered by the user */
		User* user;
		
		/** Application state */
		ApplicationState* application;
		
		/** User storage access */
		UserStorage* userStorage;
};