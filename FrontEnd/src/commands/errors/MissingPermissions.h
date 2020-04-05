#pragma once

#include "../Command.h"
#include "../../models/User.h"

/**
 * A command that runs when a user enters a command they don't
 *  have permission to run.
 */
class MissingPermissions : public Command
{
	public:
		/** Default constructor */
		MissingPermissions();
		/** Outputs an error message to the console */
		void Execute() override;
};