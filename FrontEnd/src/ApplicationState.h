#pragma once

#include "models/User.h"

/**
 * Class storing state information for the application
 */
class ApplicationState
{
	public:
		/** Default constructor. Initializes a fresh application state */
		ApplicationState();
		/** Frees the memory of the loggedInUser pointer */
		~ApplicationState();

		/**
		 * Set the currently logged in user
		 * @param user The user to specify as logged in
		 */
		void SetLoggedInUser(User* user);

		/**
		 * Check if there is a user logged in currently
		 * @returns True if there is currently a user set as the logged in user, false otherwise
		 */
		bool IsLoggedIn();
		/**
		 * Get the currently logged in user
		 * @returns The currently logged in user
		 */
		User* LoggedInUser();
		
		/**
		 * Flags that the application should exit. IsCompleted will return true
		 */
		virtual void Exit();
		/**
		 * Returns whether the application is finished running
		 * @returns true if Exit has been called, false otherwise
		 */
		virtual bool IsCompleted();

	private:
		/**
		 * The currently logged in user
		 */
		User* loggedInUser;
		bool isCompleted;
};