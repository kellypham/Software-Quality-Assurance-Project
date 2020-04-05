#pragma once
#include "../Command.h"
#include "../../ApplicationState.h"
#include "../../storage/DailyLog.h"

/**
 * Command to log out of the system
 */
class Logout : public Command
{
	public:
		/**
		 * Initializes application state and daily log file to write out to
		 * @param application State of the application
		 * @param log Daily log file to write out to
		 */
		Logout(ApplicationState* application, DailyLog* log);

		/**
		 * Unsets the application's logged in user, outputs
		 *  the contents of the log file, and clears the buffer
		 *  of the daily log file.
		 */
		void Execute() override;

	private:
		/** Application state to modify */
		ApplicationState* application;
		/** Daily log file to write to */
		DailyLog* dailyLog;
};