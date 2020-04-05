#pragma once

#include "../Command.h"

/**
 * A command returned when the user inputs a blank line.
 */
class NoCommand : public Command
{
	public:
		/** Default constructor */
		NoCommand();
		/**
		 * Returns false, this command does not require that the user
		 *  be logged in
		*/
		bool IsLoginRequired() override;
};