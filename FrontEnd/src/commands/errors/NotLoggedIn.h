#pragma once

#include "../Command.h"

/**
 * A command that runs when a user tries to run a command that
 *  requires they be logged in while they are not logged in
*/
class NotLoggedIn : public Command
{
	public:
		/** Default constructor */
		NotLoggedIn();

		/** Outputs an error message that the user must be logged in */
		void Execute() override;

		/**
		 * This command doesn't require that the user be logged in
		 * @return false
		*/
		bool IsLoginRequired() override;
};