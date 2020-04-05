#pragma once

#include <string>
#include "Command.h"
#include "../ApplicationState.h"
#include "../options/Options.h"

#include "../storage/UserStorage.h"
#include "../storage/ItemStorage.h"
#include "../storage/DailyLog.h"

/**
 * Factory to return Command objects corresponding to user input
 */
class CommandFactory
{
	public:
		/**
		 * Initializes the ApplicationState and optionsunder which this factory is to run
		 * @param state Application state
		 * @param options Runtime options
		 */
		CommandFactory(ApplicationState* state, UserStorage* userStorage, ItemStorage* itemStorage, DailyLog* log);
		/**
		 * Cleans up userStorage, itemStorage, and dailyLog that are created in this object
		 */
		~CommandFactory();
		
		/**
		 * Returns a command corresponding to the commandString parameter.
		 * Returns commands that output error messages under the following conditions:
		 * - MissingPermissions: The command matching the commandString is one which they don't
		 *     have permission to run
		 * - NotFound: commandString matches no command
		 * - NotLoggedIn: commandString matches a command which requires the user be logged in to
		 *     execute, but the user is not logged in
		 * - NoCommand: commandString is an empty string
		 * @param commandString A string corresponding to the requested command
		 * @return A command corresponding commandString
		 */
		Command* Create(std::string commandString);

	private:
		Command* PermissionCheck(Command* command);

		ApplicationState* application;
		Options* options;
		UserStorage* userStorage;
		ItemStorage* itemStorage;
		DailyLog* dailyLog;
};