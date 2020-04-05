#pragma once
#include <string>

/**
 * Abstract class definition for applicaiton settings
 */
class Options
{
	public:
		/**
		 * Validate the options values of this object.
		 * If a string is thrown from this function, the
		 *  string will be output and the application will
		 *  exit.
		 * @throw string
		*/
		virtual void Validate() = 0;

		/**
		 * Get the path to the accounts file
		 * @return A path to the user accounts file
		 */
		virtual std::string AccountsFile() = 0;

		/**
		 * Get the path to the Items file
		 * @return A path to the Items file
		 */
		virtual std::string ItemsFile() = 0;

		/**
		 * Get the path to the Daily Log file
		 * @return A path to the Daily Log file
		 */
		virtual std::string DailyLogFile() = 0;
};